package com.xjl.eimui.inputbar.recordstate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.widget.WaterWaveView;

public class AudioRecordStateView extends RelativeLayout implements RecordStateView {

    public WaterWaveView wave_view;
    public TextView chat_record_state_text;

    private final int STATE_NORMAL = 1;

    private final int STATE_CANCEL = 2;

    private int CURRENT_STATE = -1;

    public AudioRecordStateView(Context context) {
        super(context);
        initView();
    }

    public AudioRecordStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_chat_voice_record_state, this);
        wave_view = findViewById(R.id.wave_view);
        wave_view.setColor("#717cfd");
        chat_record_state_text = findViewById(R.id.chat_record_state_text);
    }

    @Override
    public void Show() {
        this.setVisibility(View.VISIBLE);
        wave_view.start();
    }

    @Override
    public void normalRecord() {
        if (CURRENT_STATE == STATE_NORMAL) {
            return;
        }
        CURRENT_STATE = STATE_NORMAL;
        chat_record_state_text.setText(getResources().getString(R.string.chat_up_move_cancel));
    }

    @Override
    public void cancelRecord() {
        if (CURRENT_STATE == STATE_NORMAL) {
            return;
        }
        CURRENT_STATE = STATE_NORMAL;
        chat_record_state_text.setText(getResources().getString(R.string.chat_up_cancel));
    }

    @Override
    public void dismiss() {
        wave_view.stop();
        this.setVisibility(GONE);
    }

    @Override
    public int currentState() {
        return CURRENT_STATE;
    }

    @Override
    public void nextState() {
        CURRENT_STATE = CURRENT_STATE == STATE_NORMAL ? STATE_CANCEL : STATE_NORMAL;
        if (CURRENT_STATE == STATE_NORMAL) {
            normalRecord();
        } else {
            cancelRecord();
        }
    }

}
