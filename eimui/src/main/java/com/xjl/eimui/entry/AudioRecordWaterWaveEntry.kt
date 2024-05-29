package com.xjl.eimui.entry;

public class AudioRecordWaterWaveEntry {

    public String chat_up_move_cancel;
    public String chat_up_cancel;

    public AudioRecordWaterWaveEntry(){
        chat_up_move_cancel="Slide up to cancel";
        chat_up_cancel="chat_up_cancel";
    }

    public AudioRecordWaterWaveEntry(String chat_up_move_cancel, String chat_up_cancel) {
        this.chat_up_move_cancel = chat_up_move_cancel;
        this.chat_up_cancel = chat_up_cancel;
    }
}
