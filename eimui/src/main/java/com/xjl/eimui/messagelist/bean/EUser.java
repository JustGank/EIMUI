package com.xjl.eimui.messagelist.bean;


public interface EUser {

    /**
     * User id.
     * @return user id, unique
     */
    String getId();

    /**
     * Display name of user
     * @return display name
     */
    String getNickname();

    /**
     * Get user avatar file path.
     * @return avatar file path
     */
    String getAvatarPath();

}
