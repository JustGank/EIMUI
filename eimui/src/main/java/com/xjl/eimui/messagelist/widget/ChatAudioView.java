package com.xjl.eimui.messagelist.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.util.ScreenUtils;

;

/**
 * Created by x33664 on 2019/3/8.
 */

public class ChatAudioView extends RelativeLayout {

    private static final String TAG = "ChatAudioView";

    private RelativeLayout audio_container;

    private ImageView voice_icon;

    private TextView time;

    private int minLengthDP = 80;
    private int maxLengthDP = 240;
    private int minRecordTimes = 10;
    private int maxRecordTimes = 60;
    private int minLength = 0;
    private int maxLength = 0;
    private ObjectAnimator objectAnimator;

    private boolean isRight = true;

    public ChatAudioView(Context context)
    {
        super(context);
        minLength = ScreenUtils.dp2px(getContext(), minLengthDP);
        maxLength = ScreenUtils.dp2px(getContext(), maxLengthDP);
        initView(isRight);
    }

    public ChatAudioView(Context context, boolean isRight) {
        super(context);
        this.isRight = isRight;
        minLength = ScreenUtils.dp2px(getContext(), minLengthDP);
        maxLength = ScreenUtils.dp2px(getContext(), maxLengthDP);
        initView(this.isRight);
    }

    private void initView(boolean mine) {
        if(mine)
        {
            LayoutInflater.from(getContext()).inflate(R.layout.view_chat_audio_sender, this);
        }else
        {
            LayoutInflater.from(getContext()).inflate(R.layout.view_chat_audio_recepter, this);
        }

        audio_container = (RelativeLayout) this.findViewById(R.id.audio_container);
        voice_icon = (ImageView) this.findViewById(R.id.voice_icon);
        time = (TextView) this.findViewById(R.id.time);
    }

    /**
     * 设置时间后动态设置item的长度
     */
    public void setTime(int timeUnitSeconds) {
        this.time.setText(timeUnitSeconds + "''");
        RelativeLayout.LayoutParams layoutParams = null;
        if (timeUnitSeconds < minRecordTimes) {
            layoutParams = new RelativeLayout.LayoutParams(minLength, LayoutParams.WRAP_CONTENT);
        } else if (timeUnitSeconds < maxRecordTimes) {
            float scale = (float) (timeUnitSeconds - minRecordTimes) / (float) (maxRecordTimes - minRecordTimes);
            int tempLength = (int) (minLength + scale * (maxLength - minLength));
            layoutParams = new RelativeLayout.LayoutParams(tempLength, LayoutParams.WRAP_CONTENT);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(maxLength, LayoutParams.WRAP_CONTENT);
        }
        audio_container.setLayoutParams(layoutParams);
    }


    public void startAnim() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(voice_icon, "alpha", 1f, 0f, 1f);
            objectAnimator.setRepeatMode(ValueAnimator.RESTART);
            objectAnimator.setRepeatCount(-1);
            objectAnimator.setDuration(2000);
        }
        if (objectAnimator.isRunning()) {
            return;
        }
        objectAnimator.start();
    }

    public void endAnim() {
        if (null != objectAnimator) {
            objectAnimator.cancel();
            objectAnimator = null;
            voice_icon.setBackgroundResource(R.mipmap.item_chat_voice);
        }
    }

    public boolean isPlaying() {
        if (objectAnimator != null) {
            return objectAnimator.isRunning();
        }
        return false;
    }

}
