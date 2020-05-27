package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class TextMessageFragment extends BaseMessageFragment{

    public final String longText = "布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下超级英雄，初次登场于《侦探漫画》（Detective Comics）第27期（1939年5月），由鲍勃·凯恩（Bob Kane）和比尔·芬格（Bill Finger）联合创造，是漫画史上第一位没有超能力的超级英雄。布鲁斯·韦恩出生在哥谭市四大家族之一的韦恩家族中。一天晚上，父母带着年幼的布鲁斯看完电影《佐罗》回家，途经一条小径时遭遇歹徒的抢劫。歹徒当着布鲁斯的面枪杀了他的父母。从此，布鲁斯便产生了亲手铲除罪恶的强烈愿望，为了不让其他人再遭受到与自己同样的悲剧，凭借着过人的天赋，布鲁斯利用十几年时间游历世界各地，拜访东西方顶级或传说中的格斗大师，学习各流派格斗术，后回到美国，利用强大的财力制造各种高科技装备。此后在白天，他是别人眼中的无脑富二代、花花公子；夜晚，他是令罪犯闻风丧胆的黑暗骑士——蝙蝠侠（Batman）。";


    public TextMessageFragment(){}

    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();
        //文字短信实例
        IMessage textMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_TEXT);
        textMessage1.setContent("Hello Bat Man");
        messages.add(textMessage1);

        IMessage textMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage2.setContent("Hello Super Man");
        textMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(textMessage2);

        IMessage textMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage3.setContent(longText);
        textMessage3.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(textMessage3);

        adapter.setList(messages);
    }
}
