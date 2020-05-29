package com.xjl.eimui.messagelist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class MessageRecycler extends RecyclerView {
    public MessageRecycler(@NonNull Context context) {
        super(context);
        initView();
    }

    public MessageRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MessageRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        setLayoutManager(linearLayoutManager);
        ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
        setOnTouchListener((View view, MotionEvent motionEvent) -> {
            closeSoftInput(view);
            return false;
        });
    }

    //关闭软键盘
    public void closeSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
