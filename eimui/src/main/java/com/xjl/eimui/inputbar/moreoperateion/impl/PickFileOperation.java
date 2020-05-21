package com.xjl.eimui.inputbar.moreoperateion.impl;

import android.app.Activity;
import android.view.View;

import com.xjl.eimui.constant.EIMConstant;
import com.xjl.emedia.utils.IntentUtil;

public class PickFileOperation implements Operation {

    @Override
    public boolean previewOperate() {
        return true;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        if (!previewOperate()) {
            return;
        }
        IntentUtil.openFileManager(activity, EIMConstant.REQUEST_CODE_PICK_FILE);
    }

    @Override
    public int getRequestCode() {
        return EIMConstant.REQUEST_CODE_PICK_FILE;
    }

}
