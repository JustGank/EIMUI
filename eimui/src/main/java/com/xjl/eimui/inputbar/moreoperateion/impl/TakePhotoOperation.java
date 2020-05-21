package com.xjl.eimui.inputbar.moreoperateion.impl;

import android.app.Activity;
import android.view.View;

import com.xjl.eimui.EIMUI;
import com.xjl.eimui.constant.EIMConstant;
import com.xjl.emedia.utils.IntentUtil;

public class TakePhotoOperation implements Operation {

    @Override
    public boolean previewOperate() {
        return true;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        if (!previewOperate()) {
            return;
        }

        IntentUtil.makePhoto(activity, EIMUI.INSTANCE.getTakePhotoSavePath(), EIMConstant.REQUEST_CODE_TAKE_PHOTO);

    }

    @Override
    public int getRequestCode() {
        return EIMConstant.REQUEST_CODE_TAKE_PHOTO;
    }

}

