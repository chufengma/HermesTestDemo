/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#include <fb/log.h>
#include <stdarg.h>
#include <stdio.h>
#include <string.h>
//CRN BEGIN
#include "fb/fbjni/CoreClasses.h"
#include "fb/Environment.h"
#include "jni/LocalString.h"
#include <sstream>

using namespace facebook::jni;
//CRN END

#define LOG_BUFFER_SIZE 4096
static LogHandler gLogHandler;

void setLogHandler(LogHandler logHandler) {
  gLogHandler = logHandler;
}

//CRN BEGIN
int checkUtfString(JNIEnv* env, const char* bytes)
{
//    const char* origBytes = bytes;
    if (bytes == NULL) {
        return -1;
    }
    while (*bytes != '\0') {
        char utf8 = *(bytes++);
        // Switch on the high four bits.
        switch (utf8 >> 4) {
            case 0x00:
            case 0x01:
            case 0x02:
            case 0x03:
            case 0x04:
            case 0x05:
            case 0x06:
            case 0x07: {
                // Bit pattern 0xxx. No need for any extra bytes.
                break;
            }
            case 0x08:
            case 0x09:
            case 0x0a:
            case 0x0b:
            case 0x0f: {
                /*
                 * Bit pattern 10xx or 1111, which are illegal start bytes.
                 * Note: 1111 is valid for normal UTF-8, but not the
                 * modified UTF-8 used here.
                 */
                //__android_log_write("JNI WARNING: illegal start byte 0x%x\n", utf8);
                return -2;
            }
            case 0x0e: {
                // Bit pattern 1110, so there are two additional bytes.
                utf8 = *(bytes++);
                if ((utf8 & 0xc0) != 0x80) {
                    //__android_log_write("JNI WARNING: illegal continuation byte 0x%x\n", utf8);
                    return -3;
                }
                // Fall through to take care of the final byte.
            }
            case 0x0c:
            case 0x0d: {
                // Bit pattern 110x, so there is one additional byte.
                utf8 = *(bytes++);
                if ((utf8 & 0xc0) != 0x80) {
                    //__android_log_write("JNI WARNING: illegal continuation byte 0x%x\n", utf8);
                    return -4;
                }
                break;
            }
        }
    }
    return 0;
}
//CRN END

int fb_printLog(int prio, const char *tag,  const char *fmt, ...) {
  char logBuffer[LOG_BUFFER_SIZE];

  va_list va_args;
  va_start(va_args, fmt);
  int result = vsnprintf(logBuffer, sizeof(logBuffer), fmt, va_args);
  va_end(va_args);
  if (gLogHandler != NULL) {
      gLogHandler(prio, tag, logBuffer);
  }
  __android_log_write(prio, tag, logBuffer);
  //CRN BEGIN
  //output log to FLog

      std::ostringstream tagBuilder;
      if (tag && strlen(tag) > 0) {
          tagBuilder << tag << "";
      } else {
          return result;
      }

      std::ostringstream logBuilder;
      logBuilder << logBuffer << "";

      try {
          auto getFBLogClass = findClassLocal("com/facebook/common/logging/FLog");
          auto fbLogMethod = getFBLogClass->getStaticMethod<void(jstring, jstring)>("v");
          switch (prio) {
              default:
              case ANDROID_LOG_UNKNOWN:
              case ANDROID_LOG_DEFAULT:
              case ANDROID_LOG_SILENT:
              case ANDROID_LOG_VERBOSE:
                  break;
              case ANDROID_LOG_DEBUG:
                  fbLogMethod = getFBLogClass->getStaticMethod<void(jstring, jstring)>("d");
                  break;
              case ANDROID_LOG_INFO:
                  fbLogMethod = getFBLogClass->getStaticMethod<void(jstring, jstring)>("i");
                  break;
              case ANDROID_LOG_WARN:
                  fbLogMethod = getFBLogClass->getStaticMethod<void(jstring, jstring)>("w");
                  break;
              case ANDROID_LOG_ERROR:
              case ANDROID_LOG_FATAL:
                  fbLogMethod = getFBLogClass->getStaticMethod<void(jstring, jstring)>("e");
                  break;
          }
          if (checkUtfString(Environment::current(), tagBuilder.str().data()) == 0 && checkUtfString(Environment::current(), logBuilder.str().data()) == 0) {
              fbLogMethod(getFBLogClass, LocalString(tagBuilder.str()).string(), LocalString(logBuilder.str()).string());
          }
      } catch (...) {
          __android_log_write(ANDROID_LOG_ERROR, "fb_printLog_error", "error broked on fb_printLog method map to Java method!");
      }

  //CRN END
  return result;
}

void logPrintByDelims(int priority, const char* tag, const char* delims,
        const char* msg, ...)
{
    va_list ap;
    char buf[32768];
    char* context;
    char* tok;

    va_start(ap, msg);
    vsnprintf(buf, sizeof(buf), msg, ap);
    va_end(ap);

    tok = strtok_r(buf, delims, &context);

    if (!tok) {
        return;
    }

    do {
        __android_log_write(priority, tag, tok);
    } while ((tok = strtok_r(NULL, delims, &context)));
}

#ifndef ANDROID

// Implementations of the basic android logging functions for non-android platforms.

static char logTagChar(int prio) {
  switch (prio) {
    default:
    case ANDROID_LOG_UNKNOWN:
    case ANDROID_LOG_DEFAULT:
    case ANDROID_LOG_SILENT:
      return ' ';
    case ANDROID_LOG_VERBOSE:
      return 'V';
    case ANDROID_LOG_DEBUG:
      return 'D';
    case ANDROID_LOG_INFO:
      return 'I';
    case ANDROID_LOG_WARN:
      return 'W';
    case ANDROID_LOG_ERROR:
      return 'E';
    case ANDROID_LOG_FATAL:
      return 'F';
  }
}

int __android_log_write(int prio, const char *tag, const char *text) {
  return fprintf(stderr, "[%c/%.16s] %s\n", logTagChar(prio), tag, text);
}

int __android_log_print(int prio, const char *tag,  const char *fmt, ...) {
  va_list ap;
  va_start(ap, fmt);

  int res = fprintf(stderr, "[%c/%.16s] ", logTagChar(prio), tag);
  res += vfprintf(stderr, "%s\n", ap);

  va_end(ap);
  return res;
}

#endif
