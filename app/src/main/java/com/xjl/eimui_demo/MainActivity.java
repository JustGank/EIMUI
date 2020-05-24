package com.xjl.eimui_demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
import com.xjl.eimui.messagelist.adapter.EMessageAdapter;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.util.ToastUtils;
import com.xjl.eimui_demo.bean.IUser;
import com.xjl.eimui_demo.bean.ImageMessage;
import com.xjl.eimui_demo.bean.TextMessage;
import com.xjl.eimui_demo.operation.LocationOperation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private IUser mine, other;

    private RecyclerView recycler;
    private EMessageAdapter adapter;

    private InputBar inputbar;
    private AudioRecordStateView recordstate_view;
    private RecordTouchListener recordTouchListener;
    private InputBarMoreDefaultAdapter inputBarMoreDefaultAdapter;

    private final String testImg1Url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=eee3d397646d86ca74b57295d0685774&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181031%2F500b3ed31f4940adbd5cb625847ef335.jpeg";
    private final String testImgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=d74bfa4dbfdcb7b878261461036988d4&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201606%2F25%2F20160625202457_xEKT3.thumb.700_0.jpeg";

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


        initUser();
        initMessageRecycler();
        initInputBar();
    }

    private void initUser() {
        mine = new IUser("0001", "Bat Man", "https://pic.17qq.com/img_qqtouxiang/32532505.jpeg");
        other = new IUser("0002", "Super Man", "https://pic.3h3.com/up/2012-12/2012121227271453263497.jpg");
    }

    private void initMessageRecycler() {
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();
        TextMessage textMessage1 = new TextMessage(String.valueOf(++messageId), "Hello Bat Man", mine, other, MessageType.RECEIVE_TEXT);
        messages.add(textMessage1);
        TextMessage textMessage2 = new TextMessage(String.valueOf(++messageId), "Hello Super Man", mine, other, MessageType.SEND_TEXT);
        textMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(textMessage2);

        ImageMessage imageMessage1 = new ImageMessage(String.valueOf(++messageId), testImg1Url, mine, other, MessageType.RECEIVE_IMAGE);
        messages.add(imageMessage1);
        ImageMessage imageMessage2 = new ImageMessage(String.valueOf(++messageId), testImgUrl2, mine, other, MessageType.SEND_IMAGE);
        imageMessage2.setMessageStatus(MessageStatus.SEND_GOING);
        messages.add(imageMessage2);


        adapter = new EMessageAdapter(MainActivity.this, messages, mine, other);
        recycler.setAdapter(adapter);
    }


    private void initInputBar() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
