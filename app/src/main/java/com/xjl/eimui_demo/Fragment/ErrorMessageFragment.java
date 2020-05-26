package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessageFragment extends BaseMessageFragment {
    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();

        //异常状态短信
        IMessage errorMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_REDOWNLOAD);
        messages.add(errorMessage1);

        IMessage errorMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FAIL_MESSAGE);
        errorMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(errorMessage2);

        adapter.setList(messages);
    }
}
