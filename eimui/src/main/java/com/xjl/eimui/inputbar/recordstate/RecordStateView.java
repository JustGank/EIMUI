package com.xjl.eimui.inputbar.recordstate;

public interface RecordStateView {

    int SHOWING=1;

    int DISMISS=2;

    int RECORDING=3;

    int CANCELRECORD=4;

    public void Show();

    public void normalRecord();

    public void cancelRecord();

    public void dismiss();

    public int currentState();

    public void nextState();

}
