package com.xjl.eimui.inputbar.recordstate;

public interface RecordStateListener {
    //1是开始录音 2是取消录音 3是发送录音

    int START_RECORD = 1;

    int CANCEL_RECORD = 2;

    int RECORD_FINISH = 3;

    /**
     * 当前录音状态的回调
     */
    void onRecordStateChange(int currentState);

    void onFrequenceClick(long downTime, long upTime);

}
