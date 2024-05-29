package com.xjl.eimui.inputbar.moreoperateion.impl;

import android.app.Activity;
import android.view.View;

public interface Operation {

    public boolean previewOperate();

    public void operate(View v, int position, Activity activity);

    public int getRequestCode();

}
