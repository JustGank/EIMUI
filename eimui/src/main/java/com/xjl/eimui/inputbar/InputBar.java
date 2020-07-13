package com.xjl.eimui.inputbar;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xjl.eimui.R;
import com.xjl.eimui.inputbar.builder.InputBarBuilder;
import com.xjl.eimui.util.ToastUtils;

public class InputBar extends LinearLayout implements View.OnClickListener {

    /**
     * LeftImg1作为预留逻辑项，默认实现切换 键盘输入/语音输入
     */
    private ImageView left_img1, left_img2, left_img3;
    private EditText editview;
    private ImageView right_img1, right_img2, right_img3;
    private RecyclerView more_panel;
    private TextView press_talk;

    private boolean currentInputStateIsKey = true;

    private InputBarBuilder inputBarBuilder;

    public InputBar(Context context) {
        super(context);
        initView();
    }

    public InputBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_message_inputbar, this);
        left_img1 = (ImageView) this.findViewById(R.id.left_img1);
        left_img1.setOnClickListener(this);

        left_img2 = (ImageView) this.findViewById(R.id.left_img2);
        left_img2.setOnClickListener(this);

        left_img3 = (ImageView) this.findViewById(R.id.left_img3);
        left_img3.setOnClickListener(this);

        editview = (EditText) this.findViewById(R.id.editview);
        editview.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66 && event.getAction() == 1) {
                    String content = editview.getText().toString();
                    if (TextUtils.isEmpty(content.trim())) {
                        ToastUtils.showMessage(getContext(), getResources().getString(R.string.cant_empty_text));
                        return false;
                    }
                    if (onItemClickListener != null) {
                        onItemClickListener.onSendClicked(content);
                    }
                }
                return false;
            }
        });

        press_talk = (TextView) this.findViewById(R.id.press_talk);

        right_img1 = (ImageView) this.findViewById(R.id.right_img1);
        right_img1.setOnClickListener(this);
        right_img2 = (ImageView) this.findViewById(R.id.right_img2);
        right_img2.setOnClickListener(this);
        right_img3 = (ImageView) this.findViewById(R.id.right_img3);
        right_img3.setOnClickListener(this);

        more_panel = (RecyclerView) this.findViewById(R.id.more_panel);

        setInputBarBuilder(inputBarBuilder = InputBarBuilder.getNewInstance());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.left_img1) {
            currentInputStateIsKey = !currentInputStateIsKey;
            if (currentInputStateIsKey) {
                editview.setVisibility(VISIBLE);
                press_talk.setVisibility(GONE);
                left_img1.setBackgroundResource(this.inputBarBuilder.getLeft_img1_voice());
            } else {
                editview.setVisibility(GONE);
                press_talk.setVisibility(VISIBLE);
                left_img1.setBackgroundResource(this.inputBarBuilder.getLeft_img1_keybord());
            }
            if (onItemClickListener != null) {
                onItemClickListener.onLeftImg1Clicked(left_img1);
            }
        } else if (id == R.id.left_img2) {
            if (onItemClickListener != null) {
                onItemClickListener.onLeftImg2Clicked(left_img2);
            }
        } else if (id == R.id.left_img3) {
            if (onItemClickListener != null) {
                onItemClickListener.onLeftImg3Clicked(left_img3);
            }
        } else if (id == R.id.right_img1) {
            more_panel.setVisibility(more_panel.getVisibility() == View.VISIBLE ? GONE : VISIBLE);
            if (onItemClickListener != null) {
                onItemClickListener.onRightImg1Clicked(right_img1);
            }
        } else if (id == R.id.right_img2) {
            if (onItemClickListener != null) {
                onItemClickListener.onRightImg2Clicked(right_img2);
            }
        } else if (id == R.id.right_img3) {
            if (onItemClickListener != null) {
                onItemClickListener.onRightImg2Clicked(right_img2);
            }
        }
    }

    public RecyclerView getMorePanel() {
        return more_panel;
    }

    public EditText getEdittext() {
        return editview;
    }

    public InputBarBuilder getInputBarBuilder() {
        return inputBarBuilder;
    }

    public void setInputBarBuilder(InputBarBuilder inputBarBuilder) {
        if (inputBarBuilder != null) {

            this.inputBarBuilder=inputBarBuilder;

            if (this.inputBarBuilder.getLeft_img1_keybord() == -1 && this.inputBarBuilder.getLeft_img1_voice() == -1) {
                left_img1.setVisibility(GONE);
            } else {
                left_img1.setVisibility(VISIBLE);
                left_img1.setBackgroundResource(currentInputStateIsKey?this.inputBarBuilder.getLeft_img1_voice():this.inputBarBuilder.getLeft_img1_keybord());
            }
            initImageView(this.inputBarBuilder.getLeft_img2_res(), left_img2);
            initImageView(this.inputBarBuilder.getLeft_img3_res(), left_img3);

            initImageView(this.inputBarBuilder.getRight_img1_res(), right_img1);
            initImageView(this.inputBarBuilder.getRight_img2_res(), right_img2);
            initImageView(this.inputBarBuilder.getRight_img3_res(), right_img3);
        }
    }

    private void initImageView(int res, ImageView imageView) {
        if (res == -1) {
            imageView.setVisibility(GONE);
        } else {
            imageView.setVisibility(VISIBLE);
            imageView.setBackgroundResource(res);
        }
    }

    /**
     * 为长按 语音输出设置 OnFocusChangeListener
     */
    public void setPressTalkOnTouchListener(OnTouchListener onTouchListener) {
        this.press_talk.setOnTouchListener(onTouchListener);
    }

    public void setEditviewFocusListener(OnFocusChangeListener onFocusChangeListener) {
        if (null != editview) {
            editview.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    /**
     * 由于控件较多 采用类的形式 减少因使用接口而暴露所有方法
     */
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class OnItemClickListener {

        public void onSendClicked(String content) {

        }

        public void onLeftImg1Clicked(ImageView img) {
        }

        public void onLeftImg2Clicked(ImageView img) {
        }

        public void onLeftImg3Clicked(ImageView img) {
        }

        public void onRightImg1Clicked(ImageView img) {
        }

        public void onRightImg2Clicked(ImageView img) {
        }

        public void onRightImg3Clicked(ImageView img) {
        }
    }
}
