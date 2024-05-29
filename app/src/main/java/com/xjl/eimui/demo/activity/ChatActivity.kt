package com.xjl.eimui_demo.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.xjl.eimui.EIMUI;
import com.xjl.eimui.constant.EIMConstant;
import com.xjl.eimui.inputbar.InputBar;
import com.xjl.eimui.inputbar.builder.InputBarBuilder;
import com.xjl.eimui.inputbar.moreoperateion.adapter.InputBarMoreDefaultAdapter;
import com.xjl.eimui.inputbar.moreoperateion.bean.ChatMoreBean;
import com.xjl.eimui.inputbar.moreoperateion.impl.PickFileOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.PickPicOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.TakePhotoOperation;
import com.xjl.eimui.inputbar.moreoperateion.impl.TakeVideoOperation;
import com.xjl.eimui.inputbar.recordstate.AudioReocrdWeChatWaveView;
import com.xjl.eimui.inputbar.recordstate.RecordStateListener;
import com.xjl.eimui.inputbar.recordstate.RecordTouchListener;
import com.xjl.eimui.messagelist.adapter.EMessageAdapter;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.listener.OperationListener;
import com.xjl.eimui.messagelist.widget.MessageRecycler;
import com.xjl.eimui.util.ToastUtils;
import com.xjl.eimui_demo.R;
import com.xjl.eimui_demo.bean.IMessage;
import com.xjl.eimui_demo.bean.TestDataFactory;
import com.xjl.eimui_demo.operation.LocationOperation;
import com.xjl.emedia.bean.MediaPickerBean;
import com.xjl.emedia.utils.FileChooseUtil;
import com.xjl.emedia.utils.IntentUtil;
import com.xjl.emp3recorder.mp3record.MP3Recorder;
import com.xjl.emp3recorder.utils.MediaPlayerHepler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "InputTestActivity";

    private MessageRecycler recycler;
    private EMessageAdapter adapter;
    private InputBar inputbar;
    private AudioReocrdWeChatWaveView recordstate_view;
    private RecordTouchListener recordTouchListener;
    private InputBarMoreDefaultAdapter inputBarMoreDefaultAdapter;

    private MP3Recorder mp3Recorder;
    private MediaPlayerHepler mediaPlayerHepler;
    private File currentAudioFile = null;

    public int messageId = 0;

    private final int MAX_FILE_LENGTH_M = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initRecycler();
        initInputBar();

        adapter.setList(TestDataFactory.getTestData(getIntent().getIntExtra("index", 1)));

        mp3Recorder = new MP3Recorder(currentAudioFile);
        mp3Recorder.setOnRecordListener(onRecordListener);
        mediaPlayerHepler = new MediaPlayerHepler(ChatActivity.this);
        mediaPlayerHepler.setOnCompleteListener(completionListener);
    }

    private void initRecycler() {
        recycler = (MessageRecycler) findViewById(R.id.recycler);
        adapter = new EMessageAdapter(ChatActivity.this, new ArrayList<>());
        recycler.setAdapter(adapter);
        adapter.setOperationListener(operationListener);
    }

    OperationListener<IMessage> operationListener = new OperationListener<IMessage>() {
        @Override
        public void onItemClickListener(int position, View v, IMessage data) {
            switch (data.getMessageType()) {
                case MessageType.RECEIVE_TEXT:
                case MessageType.SEND_TEXT:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getContent());
                    break;
                case MessageType.RECEIVE_IMAGE:
                case MessageType.SEND_IMAGE:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getMediaFilePath());
                    openFile(data.getMediaFilePath());
                    break;
                case MessageType.RECEIVE_VIDEO:
                case MessageType.SEND_VIDEO:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getMessageType());
                    //调用此方法时需要文件已正常存在!
                    openFile(data.getMediaFilePath());
                    break;
                case MessageType.RECEIVE_VOICE:
                case MessageType.SEND_VOICE:
                    if (TextUtils.isEmpty(data.getMediaFilePath())) {
                        ToastUtils.showMessage(ChatActivity.this, "录音文件为空");
                    } else {
                        if (null == mediaPlayerHepler) {
                            return;
                        }
                        if (lastPlayPosition == position) {
                            if (adapter.getItem(position).isPlaying()) {
                                adapter.updatePlaying(position, false);
                                mediaPlayerHepler.stop();
                            } else {
                                adapter.updatePlaying(position, true);
                                mediaPlayerHepler.start(adapter.getItem(position).getMediaFilePath());
                            }
                        } else {
                            if (lastPlayPosition > -1) {
                                adapter.updatePlaying(lastPlayPosition, false);
                                mediaPlayerHepler.stop();
                            }
                            lastPlayPosition = position;
                            adapter.updatePlaying(position, true);
                            mediaPlayerHepler.start(adapter.getItem(position).getMediaFilePath());
                        }
                    }
                    break;
                case MessageType.RECEIVE_LOCATION:
                case MessageType.SEND_LOCATION:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getContent());
                    break;
                case MessageType.RECEIVE_FILE:
                case MessageType.SEND_FILE:
                    if (v.getId() == R.id.item_chat_file_download) {
                        adapter.getItem(position).setProgress(5 + adapter.getItem(position).getProgress());
                        adapter.notifyItemChanged(position);
                    } else if (v.getId() == R.id.item_chat_file_container) {
                        Log.e(TAG, "onItemClickListener position=" + position + " current Progress=" + data.getProgress());
                    }
                    break;
            }
        }

        @Override
        public void onStateViewClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onStateViewClickListener position=" + position + " data=" + data.getMessageStatus());
        }
    };

    private void initInputBar() {
        inputbar = findViewById(R.id.inputbar);
        recordstate_view = findViewById(R.id.recordstate_view);

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
        inputbar.setInputBarBuilder(InputBarBuilder.getNewInstance()
                .setRight_img2_res(R.mipmap.chat_inputbar_template)
                .setInputBarBgResColor(R.color.colorPrimary));

        inputbar.setOnItemClickListener(onItemClickListener);

        //设置长按说话的动画 和回调监听者
        recordTouchListener = new RecordTouchListener(this, recordstate_view, recordStateListener);
        //将TouchListener 和Inputbar相绑定
        inputbar.setPressTalkOnTouchListener(recordTouchListener);
    }

    InputBar.OnItemClickListener onItemClickListener = new InputBar.OnItemClickListener() {
        @Override
        public void onSendClicked(String content) {
            super.onSendClicked(content);
            IMessage iMessage = new IMessage(String.valueOf(++messageId), TestDataFactory.mine, TestDataFactory.other, MessageType.SEND_TEXT);
            iMessage.setContent(content);
            iMessage.setMessageStatus(MessageStatus.SEND_GOING);
            adapter.insertItem(iMessage);
            inputbar.getEdittext().setText("");
        }

        @Override
        public void onRightImg2Clicked(ImageView img) {
            super.onRightImg2Clicked(img);
            ToastUtils.showMessage(ChatActivity.this, "您点击了自定义控件按钮");
        }
    };

    private boolean isCancelRecord = false;
    private int lastPlayPosition = -1;
    RecordStateListener recordStateListener = new RecordStateListener() {
        @Override
        public void onRecordStateChange(int currentState) {
            switch (currentState) {
                case RecordStateListener.START_RECORD:
                    isCancelRecord = false;
                    currentAudioFile = new File(EIMUI.INSTANCE.getRecordVoicePath() + File.separator + System.currentTimeMillis() + ".mp3");
                    try {
                        mp3Recorder.setFile(currentAudioFile);
                        mp3Recorder.start(60 * 1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case RecordStateListener.CANCEL_RECORD:
                    isCancelRecord = true;
                    mp3Recorder.stop();
                    break;
                case RecordStateListener.RECORD_FINISH:
                    isCancelRecord = false;
                    mp3Recorder.stop();
                    break;
            }
        }

        @Override
        public void onFrequenceClick(long downTime, long upTime) {
            ToastUtils.showMessage(ChatActivity.this, "您的点击过快");
        }
    };

    /**
     * 音频组件回调
     */
    private final int HANDLER_RESET_RECORD_VIEW = 107;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_RESET_RECORD_VIEW:
                    inputbar.clearFocus();
                    recordstate_view.dismiss();
                    break;
            }
        }
    };

    /**
     * 音频组件回调
     */
    MP3Recorder.OnRecordListener onRecordListener = new MP3Recorder.OnRecordListener() {
        @Override
        public void onStart() {
        }

        @Override
        public void onStop(File file, long l) {
            handler.sendEmptyMessage(HANDLER_RESET_RECORD_VIEW);
            if (isCancelRecord) {
                file.delete();
            } else {
                if (l < 1000 || file.length() < 1536) {
                    ToastUtils.showMessage(ChatActivity.this, getResources().getString(R.string.short_time));
                    file.delete();
                } else {
                    runOnUiThread(() -> {
                        IMessage iMessage = new IMessage(String.valueOf(++messageId), TestDataFactory.mine, TestDataFactory.other, MessageType.SEND_VOICE);
                        iMessage.setMediaFilePath(file.getPath());
                        iMessage.setDuration(l / 1000);
                        iMessage.setMessageStatus(MessageStatus.SEND_GOING);
                        adapter.insertItem(iMessage);
                    });
                }
            }
        }

        @Override
        public void onRecording(int i, int i1) {
            recordstate_view.refreshCurrenDB(i);
        }
    };


    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (mediaPlayerHepler != null) {
                mediaPlayerHepler.stop();
            }
            adapter.updatePlaying(lastPlayPosition, false);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case EIMConstant.REQUEST_CODE_PICK_PIC:
                    List<MediaPickerBean> tempList = IntentUtil.parserMediaResultData(EIMConstant.REQUEST_CODE_PICK_PIC, data);
                    if (tempList == null || tempList.size() == 0) {
                        ToastUtils.showMessage(ChatActivity.this, getResources().getString(R.string.invalid_file));
                        return;
                    }
                    sendMediaMessage(tempList.get(0).getMediaFilePath(), MessageType.SEND_IMAGE);
                    break;
                case EIMConstant.REQUEST_CODE_TAKE_PHOTO:
                    sendMediaMessage(IntentUtil.parserTakedPhoto(ChatActivity.this, true).getAbsolutePath(), MessageType.SEND_IMAGE);
                    break;
                case EIMConstant.REQUEST_CODE_RECORD:
                    sendMediaMessage(IntentUtil.parserCustomTakedVideo(ChatActivity.this, data, true).getAbsolutePath(), MessageType.SEND_VIDEO);
                    break;
                case EIMConstant.REQUEST_CODE_PICK_FILE:
                    if (FileChooseUtil.isDownloadsDocument(data.getData())) {
                        ToastUtils.showMessage(ChatActivity.this, getResources().getString(R.string.invalid_file));
                    } else {
                        String filePath = FileChooseUtil.getPathFromLocalUri(ChatActivity.this, data.getData());
                        if (!TextUtils.isEmpty(filePath)) {
                            File file = new File(filePath);
                            sendMediaMessage(file, MessageType.SEND_FILE);
                        } else {
                            ToastUtils.showMessage(ChatActivity.this, getResources().getString(R.string.invalid_file));
                        }
                    }
                    break;
            }
        }

    }

    private void sendMediaMessage(String filePath, int type) {
        IMessage iMessage = TestDataFactory.getIMessage(type);
        iMessage.setMediaFilePath(filePath);
        iMessage.setMessageStatus(MessageStatus.SEND_GOING);
        adapter.insertItem(iMessage);
    }

    private void sendMediaMessage(File file, int type) {
        IMessage iMessage = TestDataFactory.getIMessage(type);
        iMessage.setMediaFilePath(file.getAbsolutePath());
        iMessage.setContent(file.getName());
        iMessage.setMessageStatus(MessageStatus.SEND_GOING);
        adapter.insertItem(iMessage);
    }

    private void openFile(String path){
        if (new File(path).exists()) {
            IntentUtil.openMedia(this, path);
        } else {
            ToastUtils.showMessage(this, R.string.invalid_file);
        }
    }

}
