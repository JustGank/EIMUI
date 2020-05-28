package com.xjl.eimui.inputbar.recordstate;

public interface RecordStateView {

    public void Show();

    public void normalRecord();

    public void cancelRecord();

    public void dismiss();

    public int currentState();

    public void nextState();

}
