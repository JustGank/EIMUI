package com.xjl.eimui_demo.operation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.xjl.eimui.inputbar.moreoperateion.impl.Operation;
import com.xjl.eimui.util.ToastUtils;

public class LocationOperation implements Operation {
    @Override
    public boolean previewOperate() {
        return false;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        ToastUtils.showMessage(activity,"正在为您跳转高德地图");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        //将功能Scheme以URI的方式传入data
        Uri uri = Uri.parse("androidamap://navi?sourceApplication=appname&amp;poiname=fangheng&amp;lat=36.547901&amp;lon=104.258354&amp;dev=1&amp;style=2");
        intent.setData(uri);

        //启动该页面即可
        activity.startActivity(intent);
    }

    @Override
    public int getRequestCode() {
        return 0;
    }
}
