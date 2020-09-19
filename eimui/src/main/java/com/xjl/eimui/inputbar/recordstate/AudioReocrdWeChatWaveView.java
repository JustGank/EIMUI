package com.xjl.eimui.inputbar.recordstate;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;

/**
 * 仿微信录音控件 可以实时显示当前录音的分贝大小
 */
public class AudioReocrdWeChatWaveView extends RelativeLayout implements RecordStateView {

    private static final String TAG = "AudioReocrdWeChatWaveVi";

    public ImageView iv_voice_ing;
    public TextView tv_voice_ing;


    private final int STATE_NORMAL = 1;

    private final int STATE_CANCEL = 2;

    private int CURRENT_STATE = -1;

    public AudioReocrdWeChatWaveView(Context context) {
        super(context);
        initView(context);
    }

    public AudioReocrdWeChatWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_voice_record_wechat_wave_state, this);
        iv_voice_ing = view.findViewById(R.id.iv_voice_ing);
        tv_voice_ing = view.findViewById(R.id.tv_voice_ing);
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1);
    }


    @Override
    public void Show() {
        this.setVisibility(VISIBLE);
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1);
    }

    @Override
    public void normalRecord() {
        if (CURRENT_STATE == STATE_NORMAL) {
            return;
        }
        CURRENT_STATE = STATE_NORMAL;
        tv_voice_ing.setText(getResources().getString(R.string.chat_up_move_cancel));

    }

    @Override
    public void cancelRecord() {
        if (CURRENT_STATE == STATE_CANCEL) {
            return;
        }
        CURRENT_STATE = STATE_CANCEL;
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_cancel);
        tv_voice_ing.setText(getResources().getString(R.string.chat_up_cancel));

    }

    @Override
    public void dismiss() {
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

    public void refreshCurrenDB(int d) {

        if (CURRENT_STATE == STATE_CANCEL) {
            return;
        }

        post(() -> {
            if (d < 60) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1);
            } else if (d < 65) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing2);
            } else if (d < 70) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing3);
            } else if (d < 75) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing4);
            } else if (d < 80) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing5);
            } else if (d < 85) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing6);
            } else if (d < 90) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing7);
            } else {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing8);
            }
        });

    }


}
