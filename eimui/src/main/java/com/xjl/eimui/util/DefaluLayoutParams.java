package com.xjl.eimui.util;

import android.content.Context;

import com.xjl.emedia.utils.ScreenUtil;

public class DefaluLayoutParams {
    public static int getDefaultMessagePadding(Context context) {
        return ScreenUtil.dip2px(context, 5);
    }
}
