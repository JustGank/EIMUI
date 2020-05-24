package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EUser;

public class IUser implements EUser {

    private String id;

    private String nickname;

    private String avatar;

    public IUser(String id, String nickname, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public String getAvatarPath() {
        return this.avatar;
    }
}
