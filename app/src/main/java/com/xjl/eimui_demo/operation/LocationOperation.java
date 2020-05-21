package com.xjl.eimui_demo.operation;

import android.app.Activity;
import android.view.View;

import com.xjl.eimui.inputbar.moreoperateion.impl.Operation;

public class LocationOperation implements Operation {
    @Override
    public boolean previewOperate() {
        return false;
    }

    @Override
    public void operate(View v, int position, Activity activity) {

    }

    @Override
    public int getRequestCode() {
        return 0;
    }
}
