package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class LocationMessageFragment extends BaseMessageFragment {

    public LocationMessageFragment() {
    }

    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();

        //位置短信实例
        IMessage locationMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_LOCATION);
        locationMessage1.setContent("风暴龙王降临");
        locationMessage1.setSubContent("王者峡谷河道西北方向");
        messages.add(locationMessage1);

        IMessage locationMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_LOCATION);
        locationMessage2.setContent("黑暗暴君降临");
        locationMessage2.setSubContent("王者峡谷河道东南方向");
        locationMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(locationMessage2);

        adapter.setList(messages);
    }
}
