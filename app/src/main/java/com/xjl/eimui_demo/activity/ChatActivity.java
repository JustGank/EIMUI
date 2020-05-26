package com.xjl.eimui_demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.xjl.eimui.messagelist.adapter.EMessageAdapter;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.listener.OperationListener;
import com.xjl.eimui.util.ToastUtils;
import com.xjl.eimui_demo.R;
import com.xjl.eimui_demo.bean.IMessage;
import com.xjl.eimui_demo.bean.IUser;
import com.xjl.eimui_demo.operation.LocationOperation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private IUser mine, other;

    private RecyclerView recycler;
    private EMessageAdapter adapter;

    private InputBar inputbar;
    private AudioRecordStateView recordstate_view;
    private RecordTouchListener recordTouchListener;
    private InputBarMoreDefaultAdapter inputBarMoreDefaultAdapter;

    private final String longText = "布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下超级英雄，初次登场于《侦探漫画》（Detective Comics）第27期（1939年5月），由鲍勃·凯恩（Bob Kane）和比尔·芬格（Bill Finger）联合创造，是漫画史上第一位没有超能力的超级英雄。布鲁斯·韦恩出生在哥谭市四大家族之一的韦恩家族中。一天晚上，父母带着年幼的布鲁斯看完电影《佐罗》回家，途经一条小径时遭遇歹徒的抢劫。歹徒当着布鲁斯的面枪杀了他的父母。从此，布鲁斯便产生了亲手铲除罪恶的强烈愿望，为了不让其他人再遭受到与自己同样的悲剧，凭借着过人的天赋，布鲁斯利用十几年时间游历世界各地，拜访东西方顶级或传说中的格斗大师，学习各流派格斗术，后回到美国，利用强大的财力制造各种高科技装备。此后在白天，他是别人眼中的无脑富二代、花花公子；夜晚，他是令罪犯闻风丧胆的黑暗骑士——蝙蝠侠（Batman）。";

    private final String mineAvatar = "http://img3.imgtn.bdimg.com/it/u=276120026,1236011605&fm=26&gp=0.jpg";
    private final String otherAvatar = "http://imgsrc.baidu.com/forum/w%3d580/sign=af65a417e6cd7b89e96c3a8b3f254291/87e498cad1c8a786b275eed36709c93d72cf508f.jpg";

    private final String testImgUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=d74bfa4dbfdcb7b878261461036988d4&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201606%2F25%2F20160625202457_xEKT3.thumb.700_0.jpeg";
    private final String testImgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=eee3d397646d86ca74b57295d0685774&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181031%2F500b3ed31f4940adbd5cb625847ef335.jpeg";

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
        mine = new IUser("0001", "Bat Man", mineAvatar);
        other = new IUser("0002", "Super Man", otherAvatar);
    }

    private void initMessageRecycler() {
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        recycler.setLayoutManager(linearLayoutManager);
        ((SimpleItemAnimator) recycler.getItemAnimator()).setSupportsChangeAnimations(false);

        recycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                closeSoftInput(v);
                return false;
            }
        });

        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();
        //文字短信实例
//        IMessage textMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_TEXT);
//        textMessage1.setContent("Hello Bat Man");
//        messages.add(textMessage1);
//
//        IMessage textMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
//        textMessage2.setContent("Hello Super Man");
//        textMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(textMessage2);
//
//        IMessage textMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
//        textMessage3.setContent(longText);
//        textMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(textMessage3);

        //图片短信实例
//        IMessage imageMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_IMAGE);
//        imageMessage1.setMediaFilePath(testImgUrl1);
//        messages.add(imageMessage1);
//        IMessage imageMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_IMAGE);
//        imageMessage2.setMediaFilePath(testImgUrl2);
//        imageMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(imageMessage2);

        //短视频短信实例
//        IMessage videoMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VIDEO);
//        videoMessage1.setMediaFilePath(testImgUrl1);
//        messages.add(videoMessage1);
//        IMessage videoMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VIDEO);
//        videoMessage2.setMediaFilePath(testImgUrl2);
//        videoMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(videoMessage2);

        //语音短信实例
//        IMessage voiceMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VOICE);
//        voiceMessage1.setDuration(2);
//        messages.add(voiceMessage1);
//        IMessage voiceMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
//        voiceMessage2.setDuration(60);
//        voiceMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
//        messages.add(voiceMessage2);
//        IMessage voiceMessage3= new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
//        voiceMessage3.setDuration(60);
//        voiceMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(voiceMessage3);

        //地图短信实例
//        IMessage locationMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_LOCATION);
//        locationMessage1.setContent("风暴龙王降临");
//        locationMessage1.setSubContent("王者峡谷河道西北方向");
//        messages.add(locationMessage1);
//
//        IMessage locationMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_LOCATION);
//        locationMessage2.setContent("黑暗暴君降临");
//        locationMessage2.setSubContent("王者峡谷河道东南方向");
//        locationMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
//        messages.add(locationMessage2);

        //文件短信实例
        IMessage fileMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_FILE);
        fileMessage1.setContent("Hello World.java");
        messages.add(fileMessage1);

        IMessage fileMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FILE);
        fileMessage2.setContent("Hello World.pptx");
        fileMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(fileMessage2);

        adapter = new EMessageAdapter(ChatActivity.this, messages, mine, other);
        adapter.setOperationListener(operationListener);
        recycler.setAdapter(adapter);


    }

    OperationListener<IMessage> operationListener = new OperationListener<IMessage>() {

        @Override
        public void onItemClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onItemLongClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onItemLongClickListener position=" + position + " data=" + data.getMessageType());
        }
    };


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
            ToastUtils.showMessage(ChatActivity.this, "您的点击过快");
        }
    };


    InputBar.OnItemClickListener onItemClickListener = new InputBar.OnItemClickListener() {
        @Override
        public void onSendClicked(String content) {
            super.onSendClicked(content);
            ToastUtils.showMessage(ChatActivity.this, "您点击了发送");
            inputbar.getEdittext().setText("");
        }

        @Override
        public void onRightImg2Clicked(ImageView img) {
            super.onRightImg2Clicked(img);
            ToastUtils.showMessage(ChatActivity.this, "为您自动装载聊天实例");

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //关闭软键盘
    public void closeSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
