package ctrip.wireless.android.crn.dev;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.JSException;
import com.facebook.react.devsupport.RedBoxDialog;
import com.facebook.react.devsupport.WindowOverlayCompat;
import com.facebook.react.devsupport.interfaces.StackFrame;

import ctrip.wireless.android.crn.ContextHolder;
import ctrip.wireless.android.crn.utils.ThreadUtils;

/**
 * Created by jim on 2018/1/2.
 */

public class CRNDebugTool {
//    public final static int REQUEST_CODE = 2;
//
//    public final static String CRN_SP_NAME = "CRN_SETTING_SP";
//    public final static String CRN_WS_DEBUG_SWITCH = "crn_ws_debug_switch";
//
//    public static  void showDownloadTips(final Activity activity, final PackageModel model, final Error error) {
//        if (!Env.isProductEnv() && model != null && !Package.isAutomationPackage()) {
//            ThreadUtils.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    String log = "【"+model.productName+"】增量下载:";
//                    if (error != null) {
//                        log = log + "失败--domain:" + error.domain + ", code:"+ error.code;
//                    }
//                    else {
//                        log = log + "成功！(" + model.pkgURL + ")";
//                    }
//                    if (activity != null) {
//                        Toast.makeText(activity, log, Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        }
//    }
//
//    public static void addMenuEntry(final  CRNBaseActivity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(activity)) {
//            activity.startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + activity.getPackageName())), REQUEST_CODE);
//        }
//
//        ViewGroup group = (ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
//        CtripFloatDebugView debugView = new CtripFloatDebugView(activity, R.drawable.common_sym_keyboard_x);
//        debugView.setImageBounds(DeviceUtil.getPixelFromDip(40), DeviceUtil.getPixelFromDip(30));
//        debugView.setOnOpenListener(new CtripFloatDebugView.OnOpenListener() {
//            @Override
//            public void onOpen() {
//                showToolsMenu(activity);
//            }
//        });
//        group.addView(debugView, group.getChildCount());
//    }
//
//    private static void showToolsMenu(final  CRNBaseActivity activity) {
//        boolean crnIsAppEntry = CRNDebugTool.getCRNSP().getBoolean("crn_is_app_entry", false)
//                && !StringUtil.isEmpty(CRNDebugTool.getCRNSP().getString("crn_private_url", ""));
//        String entryText = crnIsAppEntry ? "恢复正常App入口" : "设置此页面为App入口";
//        String wsDebugText = CRNDebugTool.getCRNSP().getBoolean(CRN_WS_DEBUG_SWITCH, false) ? "关闭CRN Require Profile" : "开启CRN Require Profile";
//        final  CRNURL mCRNURL = activity.mCRNURL;
//        AlertDialog alertDialog = new AlertDialog.Builder(activity)
//                .setItems(new String[]{entryText, "debug调试", wsDebugText, "页面增量信息", "页面URL", "返回"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (which == 0) {
//                            clickOnSetAppEntry(activity);
//                        } else if (which == 1) {
//                            if (activity.getReactInstanceManager() != null) {
//                                activity.getReactInstanceManager().showDevOptionsDialog();
//                            }
//                        } else if (which == 2) {
//                            boolean currentSwitch = CRNDebugTool.getCRNSP().getBoolean(CRN_WS_DEBUG_SWITCH, false);
//                            CRNDebugTool.getCRNSP().edit().putBoolean(CRN_WS_DEBUG_SWITCH, !currentSwitch).commit();
//                            if (!currentSwitch){
//                                if (CRNConfig.getRouterConfig() != null){
//                                    CRNConfig.getRouterConfig().handleCRNProfile(activity);
//                                }
//                            }else {
//                                CommonUtil.showToast("已关闭CRN Require Profile, 重启App生效");
//                            }
//                        } else if (which == 3) {
//                            clickOnPageInfo(activity);
//                        } else if (which == 4){
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
//                            builder1.setTitle("URL");
//                            builder1.setMessage(mCRNURL.getUrl());
//                            builder1.setCancelable(true);
//                            builder1.setPositiveButton("Copy URL", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    ClipboardManager cm = (ClipboardManager) FoundationContextHolder.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                                    if(cm != null){
//                                        cm.setText(mCRNURL.getUrl());
//                                    }
//                                    dialog.dismiss();
//                                }
//                            });
//                            builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            builder1.show();
//                        } else if (which == 5) {
//                            activity.finish();
//                        }
//                    }
//                }).create();
//        alertDialog.show();
//    }
//
//
//    private static void clickOnSetAppEntry(final  CRNBaseActivity activity) {
//        CRNURL mCRNURL = activity.mCRNURL;
//        boolean crnIsAppEntry = CRNDebugTool.getCRNSP().getBoolean("crn_is_app_entry", false)
//                && !StringUtil.isEmpty(CRNDebugTool.getCRNSP().getString("crn_private_url", ""));
//        CRNDebugTool.getCRNSP().edit().putString("crn_private_url", crnIsAppEntry ? "" : mCRNURL.getUrl()).commit();
//        CRNDebugTool.getCRNSP().edit().putBoolean("crn_is_app_entry", !crnIsAppEntry).commit();
//    }
//
//    private static  void clickOnPageInfo(final  CRNBaseActivity activity) {
//        CRNURL mCRNURL = activity.mCRNURL;
//        if (mCRNURL != null && mCRNURL.urlStr != null && mCRNURL.urlStr.startsWith("http")) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//            String tips = "直连页面:" + mCRNURL.urlStr;
//            builder.setMessage(tips);
//            builder.setTitle("页面信息");
//            builder.setNegativeButton("Cancel", null);
//            builder.create().show();
//        } else {
//            PackageUtil.showPackageInfoForURL(activity, mCRNURL.urlStr);
//        }
//    }

    private static RedBoxDialog mRedBoxDialog;
    private static RedBoxDialog mRedBoxJSDialog;

    public static void showRedBoxDialog(final Exception rnException) {
        showRedBoxDialog(rnException, new StackFrame[] {}, false);
    }

    public static void showRedBoxDialog(final Exception rnException, boolean fromJSError) {
        showRedBoxDialog(rnException, new StackFrame[] {}, fromJSError);
    }

    public static void showRedBoxDialog(final Exception rnException, final StackFrame[] stack, final boolean fromJSError) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !Settings.canDrawOverlays(ContextHolder.context)) {
                    Toast.makeText(ContextHolder.context, "无法获取ALERT_WINDOW权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rnException == null || ContextHolder.debug) {
                    return;
                }

                String message = rnException.getMessage();
                Throwable cause = rnException.getCause();
                while (cause != null) {
                    message += "\n\n" + cause.getMessage();
                    cause = cause.getCause();
                }

                if (rnException instanceof JSException) {
                    FLog.e(ReactConstants.TAG, "Exception in native call from JS", rnException);
                    message += "\n\n" + ((JSException) rnException).getStack();
                }

                if (mRedBoxJSDialog == null) {
                    mRedBoxJSDialog = new RedBoxDialog(ContextHolder.context, null, null);
                    mRedBoxJSDialog.getWindow().setType(WindowOverlayCompat.TYPE_SYSTEM_ALERT);
                }
                if (mRedBoxDialog == null) {
                    mRedBoxDialog = new RedBoxDialog(ContextHolder.context, null, null);
                    mRedBoxDialog.getWindow().setType(WindowOverlayCompat.TYPE_SYSTEM_ALERT);
                }

                final RedBoxDialog targetRedDialog = fromJSError ? mRedBoxJSDialog : mRedBoxDialog;
                if (targetRedDialog.isShowing()) {
                    // Sometimes errors cause multiple errors to be thrown in JS in quick succession. Only
                    // show the first and most actionable one.
                    return;
                }

                Pair<String, StackFrame[]> errorInfo = Pair.create(message, stack);
                StackFrame[] errorFrame = errorInfo.second;
                if (errorFrame == null) {
                    errorFrame = new StackFrame[0];
                }
                targetRedDialog.setExceptionDetails(errorInfo.first, errorFrame);

                // Only report native errors here. JS errors are reported
                // inside {@link #updateJSError} after source mapping.
                if (fromJSError) {
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            targetRedDialog.show();
                        }
                    }, 300);
                } else {
                    targetRedDialog.show();
                }
            }
        });
    }


}
