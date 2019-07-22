package ctrip.wireless.android.crn.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yfchu
 * @date 2016/3/17
 */
public class ThreadUtils {

    private static Handler mainHandler;

    private static ScheduledExecutorService executor;

    private static TaskHandleProxy proxy = null;

    public static void setTaskProxy(TaskHandleProxy taskProxy) {
        proxy = taskProxy;
    }

    public static void runOnUiThread(Runnable runnable) {
        internalRunOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, long delayMillis) {
        internalRunOnUiThread(runnable, delayMillis);
    }

    public static void runOnBackgroundThread(Runnable runnable) {
        if (proxy != null) {
            proxy.proxy(runnable);
        } else {
            getExecutor().execute(runnable);
        }
    }

    public static void runOnBackgroundThread(Runnable runnable, long delay) {
        getExecutor().schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    private static void internalRunOnUiThread(Runnable runnable, long delayMillis) {
        getMainHandler();
        mainHandler.postDelayed(runnable, delayMillis);
    }

    public static void post(Runnable runnable) {
        getMainHandler().post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        getMainHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * Removes the posted {@link Runnable}.
     *
     * @param runnable the {@link Runnable} to be removed.
     */
    public static void removeCallback(Runnable runnable) {
        getMainHandler().removeCallbacks(runnable);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static synchronized ScheduledExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newScheduledThreadPool(10);
        }
        return executor;
    }

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    /**
     * 忽略当前线程，同步主线程执行相应任务
     */
    public static <T> T syncToRunOnMainThread(@NonNull final Callable<T> callable, long wait) throws Throwable {
        if (isMainThread()) {
            return callable.call();
        } else {
            final AtomicReference<T> atomicRef = new AtomicReference<>();
            final AtomicReference<Throwable> atomicThw = new AtomicReference<>();
            final CountDownLatch downLatch = new CountDownLatch(1);
            getMainHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        atomicRef.set(callable.call());
                    } catch (Throwable thw) {
                        atomicThw.set(thw);
                    }
                    downLatch.countDown();
                }
            }, wait);

            try {
                downLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            Throwable thw = atomicThw.get();
            if (thw != null) {
                throw thw;
            }

            return atomicRef.get();
        }
    }

    public interface TaskHandleProxy {
        void proxy(Runnable runnable);
    }

}
