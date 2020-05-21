package com.xjl.eimui.inputbar.moreoperateion.impl;

import android.app.Activity;
import android.view.View;

import com.xjl.eimui.constant.EIMConstant;
import com.xjl.emedia.builder.EPickerBuilder;

public class PickPicOperation  implements Operation {

    @Override
    public boolean previewOperate() {
        return true;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        if (!previewOperate()) {
            return;
        }
        new EPickerBuilder(activity)
                .setPickerType(EPickerBuilder.PickerType.PHOTO_VIDEO)
                .setMaxChoseNum(1)
                .overSizeVisible(true)
                .setFilterPhotoMaxSize(10)
                .setOpenPreview(false)
                .startPicker(EIMConstant.REQUEST_CODE_PICK_PIC);
    }

    @Override
    public int getRequestCode() {
        return EIMConstant.REQUEST_CODE_PICK_PIC;
    }
}
