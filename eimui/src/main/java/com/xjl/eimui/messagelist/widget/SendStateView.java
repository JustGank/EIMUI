package com.xjl.eimui.messagelist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;

;


/**
 * Created by x33664 on 2019/1/8.
 */

public class SendStateView extends RelativeLayout {

    private final String TAG = SendStateView.class.getSimpleName();

    private ImageView error_img;
    private TextView error_text;
    private ProgressBar progress_bar;

    //0状态代表发送成功过 1代表发送中 2代表发送失败，2状态可点击 可重新发送
    private int currentState = 1;

    public SendStateView(Context context) {
        super(context);
        initView();
    }

    public SendStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_chat_send_state, this);
        error_img = (ImageView) findViewById(R.id.error_img);
        error_text = (TextView) findViewById(R.id.error_text);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void showProgressBar() {
        currentState = 1;
        this.setVisibility(VISIBLE);
        this.error_text.setVisibility(GONE);
        this.error_img.setVisibility(GONE);
        progress_bar.setVisibility(VISIBLE);
    }

    public void showError() {
        Log.e(TAG, "SendStateView showError()");
        currentState = 2;
        this.setVisibility(VISIBLE);
        this.error_text.setVisibility(VISIBLE);
        this.error_img.setVisibility(VISIBLE);
        progress_bar.setVisibility(GONE);
    }

    public void dismiss() {
        currentState = 0;
        this.error_text.setVisibility(GONE);
        this.error_img.setVisibility(GONE);
        progress_bar.setVisibility(GONE);
        this.setVisibility(GONE);

    }


    public int getCurrentState() {
        return currentState;
    }


    public void setCurrentState(int stage) {
        this.currentState = stage;
        switch (stage) {
            case 0:
                dismiss();
                break;
            case 1:
                showProgressBar();
                break;
            case 2:
                showError();
                break;
        }
    }


}
