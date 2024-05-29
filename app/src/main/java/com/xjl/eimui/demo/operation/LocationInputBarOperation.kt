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
        Uri uri = Uri.parse("https://www.amap.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    @Override
    public int getRequestCode() {
        return 0;
    }
}
