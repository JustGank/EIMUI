package com.xjl.eimui.messagelist.bean;

import android.text.SpannableString;

public interface EConversation {

    String getId();

    String getTitle();

    //可用于显示职位 或者 VIP等级
    int getTitleImg();

    String getCover();

    int unreadCount();

    SpannableString getLastMsg();

    //如是否免打扰
    int getConversationStateImgRes();


}
