package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.Fragment.ErrorMessageFragment;
import com.xjl.eimui_demo.Fragment.FileMessageFragment;
import com.xjl.eimui_demo.Fragment.LocationMessageFragment;
import com.xjl.eimui_demo.Fragment.VideoMessageFragment;
import com.xjl.eimui_demo.Fragment.VoiceMessageFragment;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static final String longText = "布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下超级英雄，初次登场于《侦探漫画》（Detective Comics）第27期（1939年5月），由鲍勃·凯恩（Bob Kane）和比尔·芬格（Bill Finger）联合创造，是漫画史上第一位没有超能力的超级英雄。布鲁斯·韦恩出生在哥谭市四大家族之一的韦恩家族中。一天晚上，父母带着年幼的布鲁斯看完电影《佐罗》回家，途经一条小径时遭遇歹徒的抢劫。歹徒当着布鲁斯的面枪杀了他的父母。从此，布鲁斯便产生了亲手铲除罪恶的强烈愿望，为了不让其他人再遭受到与自己同样的悲剧，凭借着过人的天赋，布鲁斯利用十几年时间游历世界各地，拜访东西方顶级或传说中的格斗大师，学习各流派格斗术，后回到美国，利用强大的财力制造各种高科技装备。此后在白天，他是别人眼中的无脑富二代、花花公子；夜晚，他是令罪犯闻风丧胆的黑暗骑士——蝙蝠侠（Batman）。";

    public static final String testImgUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=d74bfa4dbfdcb7b878261461036988d4&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201606%2F25%2F20160625202457_xEKT3.thumb.700_0.jpeg";
    public static final String testImgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590337851769&di=eee3d397646d86ca74b57295d0685774&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181031%2F500b3ed31f4940adbd5cb625847ef335.jpeg";

    public static final String mineAvatar = "http://img3.imgtn.bdimg.com/it/u=276120026,1236011605&fm=26&gp=0.jpg";
    public static final String otherAvatar = "http://imgsrc.baidu.com/forum/w%3d580/sign=af65a417e6cd7b89e96c3a8b3f254291/87e498cad1c8a786b275eed36709c93d72cf508f.jpg";

    public static IUser mine = new IUser("0001", "Bat Man", mineAvatar);
    public static IUser other = new IUser("0002", "Super Man", otherAvatar);

    public static List<EMessage> getTestData(int index) {
        List<EMessage> messages = null;
        switch (index) {
            case 0:

                break;
            case 1:
                return getTextTestData();
            break;
            case 2:
                return getImageTestData();
            break;
            case 3:
                fragment = new VideoMessageFragment();
                break;
            case 4:
                fragment = new VoiceMessageFragment();
                break;
            case 5:
                fragment = new LocationMessageFragment();
                break;
            case 6:
                fragment = new FileMessageFragment();
                break;
            case 7:
                fragment = new ErrorMessageFragment();
                break;
        }
        return messages;
    }

    public static List<EMessage> getTextTestData() {
        int messageId = 0;
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

        return messages;
    }

    public static List<EMessage> getImageTestData() {
        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();
        //图片短信实例
        IMessage imageMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_IMAGE);
        imageMessage1.setMediaFilePath(testImgUrl1);
        messages.add(imageMessage1);
        IMessage imageMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_IMAGE);
        imageMessage2.setMediaFilePath(testImgUrl2);
        imageMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(imageMessage2);
        return messages;
    }


}
