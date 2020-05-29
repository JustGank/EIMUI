package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;

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
                return getMuiltTestData();
            case 1:
                return getTextTestData();
            case 2:
                return getImageTestData();
            case 3:
                return getVideoTestData();
            case 4:
                return getVoiceTestData();
            case 5:
                return getLocationTestData();
            case 6:
                return getFileTestData();
            case 7:
                return getErrorTestData();
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


    public static List<EMessage> getVideoTestData() {
        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();
        //短视频短信实例
        IMessage videoMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VIDEO);
        videoMessage1.setMediaFilePath(testImgUrl1);
        messages.add(videoMessage1);
        IMessage videoMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VIDEO);
        videoMessage2.setMediaFilePath(testImgUrl2);
        videoMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(videoMessage2);
        return messages;
    }

    public static List<EMessage> getVoiceTestData() {
        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();
        //声音短信实例
        IMessage voiceMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VOICE);
        voiceMessage1.setDuration(2);
        messages.add(voiceMessage1);
        IMessage voiceMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage2.setDuration(60);
        voiceMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(voiceMessage2);
        IMessage voiceMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage3.setDuration(60);
        voiceMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(voiceMessage3);
        return messages;
    }

    public static List<EMessage> getLocationTestData() {
        int messageId = 0;
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
        return messages;
    }

    public static List<EMessage> getFileTestData() {
        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();

        //文件信息
        IMessage fileMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_FILE);
        fileMessage1.setContent("Hello World.java");
        messages.add(fileMessage1);

        IMessage fileMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FILE);
        fileMessage2.setContent("Hello World.pptx");
        fileMessage2.setProgress(30);
        fileMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(fileMessage2);
        return messages;
    }

    public static List<EMessage> getErrorTestData() {
        int messageId = 0;
        List<EMessage> messages = new ArrayList<>();

        //异常状态短信
        IMessage errorMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_REDOWNLOAD);
        messages.add(errorMessage1);

        IMessage errorMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FAIL_MESSAGE);
        errorMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(errorMessage2);

        return messages;
    }

    public static List<EMessage> getMuiltTestData() {
        int messageId = 0;

        List<EMessage> messages = new ArrayList<>();

        //文字短信实例
        IMessage textMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_TEXT);
        textMessage1.setContent("Hello Bat Man");
        messages.add(textMessage1);

        IMessage textMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage2.setContent("Hello Super Man");
        textMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(textMessage2);

        IMessage textMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage3.setContent(longText);
        textMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(textMessage3);

        //图片短信实例
        IMessage imageMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_IMAGE);
        imageMessage1.setMediaFilePath(testImgUrl1);
        messages.add(imageMessage1);
        IMessage imageMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_IMAGE);
        imageMessage2.setMediaFilePath(testImgUrl2);
        imageMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(imageMessage2);

        //短视频短信实例
        IMessage videoMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VIDEO);
        videoMessage1.setMediaFilePath(testImgUrl1);
        messages.add(videoMessage1);
        IMessage videoMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VIDEO);
        videoMessage2.setMediaFilePath(testImgUrl2);
        videoMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(videoMessage2);

        //声音短信实例
        IMessage voiceMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VOICE);
        voiceMessage1.setDuration(2);
        messages.add(voiceMessage1);
        IMessage voiceMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage2.setDuration(60);
        voiceMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(voiceMessage2);
        IMessage voiceMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage3.setDuration(60);
        voiceMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(voiceMessage3);

        //位置短信实例
        IMessage locationMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_LOCATION);
        locationMessage1.setContent("风暴龙王降临");
        locationMessage1.setSubContent("王者峡谷河道西北方向");
        messages.add(locationMessage1);

        IMessage locationMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_LOCATION);
        locationMessage2.setContent("黑暗暴君降临");
        locationMessage2.setSubContent("王者峡谷河道东南方向");
        locationMessage2.setMessageStatus(MessageStatus.SEND_FAILED);

        //文件短信实例
        IMessage fileMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_FILE);
        fileMessage1.setContent("Hello World.java");
        messages.add(fileMessage1);

        IMessage fileMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FILE);
        fileMessage2.setContent("Hello World.pptx");
        fileMessage2.setProgress(30);
        fileMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(fileMessage2);

        //异常状态短信
        IMessage errorMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_REDOWNLOAD);
        messages.add(errorMessage1);

        IMessage errorMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FAIL_MESSAGE);
        errorMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(errorMessage2);

        messages.add(locationMessage2);

        return messages;
    }

}
