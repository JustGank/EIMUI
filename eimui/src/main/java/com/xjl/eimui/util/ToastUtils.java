package com.xjl.eimui.util;

/**
 * Created by h22272 on 2018/7/5.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static final Object synObj = new Object();

    public ToastUtils() {
    }

    public static void showMessage(Context context, String text) {
        showMessage(context, text, Toast.LENGTH_SHORT);
    }

    public static void showMessage(Context context, int resId) {
        showMessage(context, resId, Toast.LENGTH_SHORT);
    }

    public static void showMessage(final Context context, final String text, final int duration) {
                ToastUtils.handler.post(new Runnable() {
                    public void run() {
                        synchronized(ToastUtils.synObj) {
                            if(ToastUtils.toast != null) {
                                ToastUtils.toast.setText(text);
                                ToastUtils.toast.setDuration(duration);
                            } else {
                                if(null!=context)
                                ToastUtils.toast = Toast.makeText(context, text, duration);
                            }

//                            ToastUtils.toast.setGravity(17, 0, 0);
                            ToastUtils.toast.show();
                        }
                    }
                });
    }

    public static void showMessage(final Context context, final int resId, final int duration) {
                synchronized(ToastUtils.synObj) {
                    if(ToastUtils.toast != null) {
                        ToastUtils.toast.setText(resId);
                        ToastUtils.toast.setDuration(duration);
                    } else {
                        ToastUtils.toast = Toast.makeText(context, resId, duration);
                    }

                    ToastUtils.toast.setGravity(17, 0, 0);
                    ToastUtils.toast.show();
                }
    }

    public static void showMessage(final Context context, final int resId, final Object... formatArgs) {
                ToastUtils.handler.post(new Runnable() {
                    public void run() {
                        synchronized (ToastUtils.synObj) {
                            if (ToastUtils.toast != null) {
                                ToastUtils.toast.setText(context.getString(resId, formatArgs));
                                ToastUtils.toast.setDuration(Toast.LENGTH_SHORT);
                            } else {
                                ToastUtils.toast = Toast.makeText(context, context.getString(resId, formatArgs), Toast.LENGTH_SHORT);
                            }
                            ToastUtils.toast.setGravity(17, 0, 0);
                            ToastUtils.toast.show();
                        }
                    }
                });
    }
}