package com.xjl.eimui_demo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.xjl.eimui.inputbar.InputBar;
import com.xjl.eimui.inputbar.builder.InputBarBuilder;
import com.xjl.eimui.inputbar.moreoperateion.adapter.InputBarMoreDefaultAdapter;
import com.xjl.eimui.inputbar.moreoperateion.bean.ChatMoreBean;
import com.xjl.eimui.inputbar.moreoperateion.impl.PickFileOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.PickPicOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.TakePhotoOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.TakeVideoOperation;
import com.xjl.eimui.inputbar.recordstate.AudioRecordStateView;
import com.xjl.eimui.inputbar.recordstate.RecordStateListener;
import com.xjl.eimui.inputbar.recordstate.RecordTouchListener;
import com.xjl.eimui.util.ToastUtils;
import com.xjl.eimui_demo.operation.LocationOperation;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private InputBar inputbar;
    private AudioRecordStateView recordstate_view;
    private RecordTouchListener recordTouchListener;
    private InputBarMoreDefaultAdapter inputBarMoreDefaultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 1001);
        }

        initView();
    }

    private void initView() {
        recycler = findViewById(R.id.recycler);
        inputbar = findViewById(R.id.inputbar);
        recordstate_view = findViewById(R.id.recordstate_view);

        //设置长按说话的动画 和回调监听者
        recordTouchListener = new RecordTouchListener(this, recordstate_view, recordStateListener);
        //将TouchListener 和Inputbar相绑定
        inputbar.setPressTalkOnTouchListener(recordTouchListener);

        //初始化inputBar的更多操作
        List<ChatMoreBean> chatMoreBeans = new ArrayList<>();
        //使用默认Operation
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_pick_pic, getString(R.string.chat_pick_pic), new PickPicOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_take_photo, getString(R.string.chat_take_photo), new TakePhotoOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_take_video, getString(R.string.chat_take_video), new TakeVideoOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_pick_file, getString(R.string.chat_file), new PickFileOperation()));
        //自定义Operation
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_location, getString(R.string.chat_location), new LocationOperation()));

        //设置更多操作
        inputBarMoreDefaultAdapter = new InputBarMoreDefaultAdapter(chatMoreBeans, this);
        inputbar.getMorePanel().setLayoutManager(new GridLayoutManager(this, 4));
        inputbar.getMorePanel().setAdapter(inputBarMoreDefaultAdapter);

        //设置InputBar 子控件监听 增加一个自定义控件
        inputbar.setInputBarBuilder(InputBarBuilder.getNewInstance().setRight_img2_res(R.mipmap.chat_inputbar_template));
        inputbar.setOnItemClickListener(onItemClickListener);
    }

    RecordStateListener recordStateListener = new RecordStateListener() {
        @Override
        public void onRecordStateChange(int currentState) {
            switch (currentState) {
                case RecordStateListener.START_RECORD:
                    break;
                case RecordStateListener.CANCEL_RECORD:
                    break;
                case RecordStateListener.RECORD_FINISH:
                    break;
            }
        }

        @Override
        public void onFrequenceClick(long downTime, long upTime) {
            ToastUtils.showMessage(MainActivity.this, "您的点击过快");
        }
    };


    InputBar.OnItemClickListener onItemClickListener = new InputBar.OnItemClickListener() {
        @Override
        public void onSendClicked(String content) {
            super.onSendClicked(content);
            ToastUtils.showMessage(MainActivity.this, "您点击了发送");
            inputbar.getEdittext().setText("");
        }

        @Override
        public void onRightImg2Clicked(ImageView img) {
            super.onRightImg2Clicked(img);
            ToastUtils.showMessage(MainActivity.this, "为您自动装载聊天实例");

        }
    };


}
