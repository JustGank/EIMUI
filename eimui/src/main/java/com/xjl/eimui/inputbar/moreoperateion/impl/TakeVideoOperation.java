package com.xjl.eimui.inputbar.moreoperateion.impl;

import android.app.Activity;
import android.view.View;

import com.xjl.eimui.EIMUI;
import com.xjl.eimui.constant.EIMConstant;
import com.xjl.emedia.builder.ERecordBuilder;

public class TakeVideoOperation implements Operation {

    @Override
    public boolean previewOperate() {
        return true;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        if (!previewOperate()) {
            return;
        }

        new ERecordBuilder(activity)
                .setRecordMinTime(3)
                .setLimitTime(20)
                .setQuality(ERecordBuilder.RecordQuality.QUALITY_480P)
                .setShowLight(false)
                .setShowRatio(false)
                .startRecord(EIMConstant.REQUEST_CODE_RECORD, EIMUI.INSTANCE.getTakeVideoSavePath());
    }

    @Override
    public int getRequestCode() {
        return EIMConstant.REQUEST_CODE_RECORD;
    }

}
