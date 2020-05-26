package com.xjl.eimui_demo.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjl.eimui.messagelist.adapter.EMessageAdapter;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.listener.OperationListener;
import com.xjl.eimui_demo.R;
import com.xjl.eimui_demo.bean.IMessage;
import com.xjl.eimui_demo.bean.IUser;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public abstract class BaseMessageFragment extends Fragment {

    private static final String TAG = "BaseMessageFragment";

    private View rootView;
    public RecyclerView recyclerView;
    public EMessageAdapter adapter;

    public IUser mine, other;
    public int messageId = 0;

    public final String longText = "布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下超级英雄，初次登场于《侦探漫画》（Detective Comics）第27期（1939年5月），由鲍勃·凯恩（Bob Kane）和比尔·芬格（Bill Finger）联合创造，是漫画史上第一位没有超能力的超级英雄。布鲁斯·韦恩出生在哥谭市四大家族之一的韦恩家族中。一天晚上，父母带着年幼的布鲁斯看完电影《佐罗》回家，途经一条小径时遭遇歹徒的抢劫。歹徒当着布鲁斯的面枪杀了他的父母。从此，布鲁斯便产生了亲手铲除罪恶的强烈愿望，为了不让其他人再遭受到与自己同样的悲剧，凭借着过人的天赋，布鲁斯利用十几年时间游历世界各地，拜访东西方顶级或传说中的格斗大师，学习各流派格斗术，后回到美国，利用强大的财力制造各种高科技装备。此后在白天，他是别人眼中的无脑富二代、花花公子；夜晚，他是令罪犯闻风丧胆的黑暗骑士——蝙蝠侠（Batman）。";

    public final String mineAvatar = "http://img3.imgtn.bdimg.com/it/u=276120026,1236011605&fm=26&gp=0.jpg";
    public final String otherAvatar = "http://imgsrc.baidu.com/forum/w%3d580/sign=af65a417e6cd7b89e96c3a8b3f254291/87e498cad1c8a786b275eed36709c93d72cf508f.jpg";

    public final String testImgUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=d74bfa4dbfdcb7b878261461036988d4&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201606%2F25%2F20160625202457_xEKT3.thumb.700_0.jpeg";
    public final String testImgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=eee3d397646d86ca74b57295d0685774&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181031%2F500b3ed31f4940adbd5cb625847ef335.jpeg";


    public BaseMessageFragment() {
    }

    private void initUser() {
        mine = new IUser("0001", "Bat Man", mineAvatar);
        other = new IUser("0002", "Super Man", otherAvatar);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message, null);
        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initUser();
        adapter = new EMessageAdapter(getContext(), new ArrayList<>(),mine,other);
        recyclerView.setAdapter(adapter);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        adapter.setOperationListener(operationListener);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();
    }


    abstract void loadData();


    OperationListener<IMessage> operationListener = new OperationListener<IMessage>() {

        @Override
        public void onHeaderClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onHeaderClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onHeaderLongClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onHeaderLongClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onOtherInfoClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onOtherInfoClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onOtherInfoLongClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onOtherInfoLongClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onMineInfoClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onMineInfoClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onMineInfoLongClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onMineInfoClickListener position=" + position + " data=" + data.getMessageType());
        }

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
                    break;

                case MessageType.RECEIVE_VIDEO:
                case MessageType.SEND_VIDEO:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getMessageType());
                    break;
                case MessageType.RECEIVE_VOICE:
                case MessageType.SEND_VOICE:
                    Log.e(TAG, "onItemClickListener position=" + position + " data=" + data.getDuration());
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
        public void onItemLongClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onItemLongClickListener position=" + position + " data=" + data.getMessageType());
        }

        @Override
        public void onStateViewClickListener(int position, View v, IMessage data) {
            Log.e(TAG, "onStateViewClickListener position=" + position + " data=" + data.getMessageStatus());

        }
    };
}
