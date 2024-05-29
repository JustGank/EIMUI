package com.xjl.eimui.demo.bean


import com.xjl.eimui.messagelist.bean.MessageStatus
import com.xjl.eimui.messagelist.bean.MessageType

object TestDataFactory {
    const val longText =
        "布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下超级英雄，初次登场于《侦探漫画》（Detective Comics）第27期（1939年5月），由鲍勃·凯恩（Bob Kane）和比尔·芬格（Bill Finger）联合创造，是漫画史上第一位没有超能力的超级英雄。布鲁斯·韦恩出生在哥谭市四大家族之一的韦恩家族中。一天晚上，父母带着年幼的布鲁斯看完电影《佐罗》回家，途经一条小径时遭遇歹徒的抢劫。歹徒当着布鲁斯的面枪杀了他的父母。从此，布鲁斯便产生了亲手铲除罪恶的强烈愿望，为了不让其他人再遭受到与自己同样的悲剧，凭借着过人的天赋，布鲁斯利用十几年时间游历世界各地，拜访东西方顶级或传说中的格斗大师，学习各流派格斗术，后回到美国，利用强大的财力制造各种高科技装备。此后在白天，他是别人眼中的无脑富二代、花花公子；夜晚，他是令罪犯闻风丧胆的黑暗骑士——蝙蝠侠（Batman）。"
    const val testImgUrl1 = "https://pics0.baidu.com/feed/95eef01f3a292df545b39ff867fe4d6d36a873c3.jpeg"
    const val testImgUrl2 = "https://i2.hdslb.com/bfs/archive/5c35a41e7643858665b787b0ff27d893a68482a3.jpg"
    const val mineAvatar = "https://tupian.qqw21.com/article/UploadPic/2012-12/201212231024869322.jpg"
    const val otherAvatar = "https://img1.baidu.com/it/u=4250803601,1786088380&fm=253&fmt=auto&app=138&f=JPEG?w=357&h=358"

    var mine = IUser("0001", "Bat Man", mineAvatar)

    var other = IUser("0002", "Super Man", otherAvatar)
    var messageId = 0

    fun getTestData(index: Int): List<IMessage> {
        when (index) {
            0 -> return muiltTestData
            1 -> return textTestData
            2 -> return imageTestData
            3 -> return videoTestData
            4 -> return voiceTestData
            5 -> return locationTestData
            6 -> return fileTestData
            else -> return errorTestData
        }
    }

    val textTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //文字短信实例
            val textMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_TEXT)
            textMessage1.content = "Hello Bat Man"
            messages.add(textMessage1)
            val textMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_TEXT)
            textMessage2.content = "Hello Super Man"
            textMessage2.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(textMessage2)
            val textMessage3 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_TEXT)
            textMessage3.content = longText
            textMessage3.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(textMessage3)
            return messages
        }
    val imageTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //图片短信实例
            val imagIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_IMAGE)
            imagIMessage1.mediaFilePath = testImgUrl1
            messages.add(imagIMessage1)
            val imagIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_IMAGE)
            imagIMessage2.mediaFilePath = testImgUrl2
            imagIMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(imagIMessage2)
            return messages
        }
    val videoTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //短视频短信实例
            val videoMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_VIDEO)
            videoMessage1.mediaFilePath = testImgUrl1
            messages.add(videoMessage1)
            val videoMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VIDEO)
            videoMessage2.mediaFilePath = testImgUrl1
            videoMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(videoMessage2)
            return messages
        }
    val voiceTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //声音短信实例
            val voicIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_VOICE)
            voicIMessage1.duration = 2
            messages.add(voicIMessage1)
            val voicIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VOICE)
            voicIMessage2.duration = 60
            voicIMessage2.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(voicIMessage2)
            val voicIMessage3 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VOICE)
            voicIMessage3.duration = 60
            voicIMessage3.messageStatus=MessageStatus.SEND_FAILED
            messages.add(voicIMessage3)
            return messages
        }
    val locationTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //位置短信实例
            val locationMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_LOCATION)
            locationMessage1.content = "风暴龙王降临"
            locationMessage1.subContent = "王者峡谷河道西北方向"
            messages.add(locationMessage1)
            val locationMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_LOCATION)
            locationMessage2.content = "黑暗暴君降临"
            locationMessage2.subContent = "王者峡谷河道东南方向"
            locationMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(locationMessage2)
            return messages
        }
    val fileTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //文件信息
            val filIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_FILE)
            filIMessage1.content = "Hello World.java"
            messages.add(filIMessage1)
            val filIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_FILE)
            filIMessage2.content = "Hello World.pptx"
            filIMessage2.progress=30
            filIMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(filIMessage2)
            return messages
        }
    val errorTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //异常状态短信
            val errorMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_REDOWNLOAD)
            messages.add(errorMessage1)
            val errorMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_FAIL_MESSAGE)
            errorMessage2.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(errorMessage2)
            return messages
        }
    val muiltTestData: List<IMessage>
        get() {
            val messages: MutableList<IMessage> = ArrayList()
            //文字短信实例
            val textMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_TEXT)
            textMessage1.content = "Hello Bat Man"
            messages.add(textMessage1)
            val textMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_TEXT)
            textMessage2.content = "Hello Super Man"
            textMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(textMessage2)
            val textMessage3 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_TEXT)
            textMessage3.content = longText
            textMessage3.messageStatus=MessageStatus.SEND_FAILED
            messages.add(textMessage3)

            //图片短信实例
            val imagIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_IMAGE)
            imagIMessage1.mediaFilePath = testImgUrl1
            messages.add(imagIMessage1)
            val imagIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_IMAGE)
            imagIMessage2.mediaFilePath = testImgUrl1
            imagIMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(imagIMessage2)

            //短视频短信实例
            val videoMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_VIDEO)
            videoMessage1.mediaFilePath = testImgUrl1
            messages.add(videoMessage1)
            val videoMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VIDEO)
            videoMessage2.mediaFilePath = testImgUrl1
            videoMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(videoMessage2)

            //声音短信实例
            val voicIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_VOICE)
            voicIMessage1.duration = 2
            messages.add(voicIMessage1)
            val voicIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VOICE)
            voicIMessage2.duration = 60
            voicIMessage2.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(voicIMessage2)
            val voicIMessage3 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_VOICE)
            voicIMessage3.duration = 60
            voicIMessage3.messageStatus=MessageStatus.SEND_FAILED
            messages.add(voicIMessage3)

            //位置短信实例
            val locationMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_LOCATION)
            locationMessage1.content = "风暴龙王降临"
            locationMessage1.subContent = "王者峡谷河道西北方向"
            messages.add(locationMessage1)
            val locationMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_LOCATION)
            locationMessage2.content = "黑暗暴君降临"
            locationMessage2.subContent = "王者峡谷河道东南方向"
            locationMessage2.messageStatus=MessageStatus.SEND_FAILED

            //文件短信实例
            val filIMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_FILE)
            filIMessage1.content = "Hello World.java"
            messages.add(filIMessage1)
            val filIMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_FILE)
            filIMessage2.content = "Hello World.pptx"
            filIMessage2.progress=30
            filIMessage2.messageStatus=MessageStatus.SEND_FAILED
            messages.add(filIMessage2)

            //异常状态短信
            val errorMessage1 =
                IMessage((messageId++).toString(), mine, other, MessageType.RECEIVE_REDOWNLOAD)
            messages.add(errorMessage1)
            val errorMessage2 =
                IMessage((messageId++).toString(), mine, other, MessageType.SEND_FAIL_MESSAGE)
            errorMessage2.messageStatus=MessageStatus.SEND_SUCCEED
            messages.add(errorMessage2)
            messages.add(locationMessage2)
            return messages
        }

    @JvmStatic
    fun getIMessage(type: Int): IMessage {
        return IMessage((messageId++).toString(), mine, other, type)
    }
}
