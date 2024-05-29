package com.xjl.eimui.messagelist.bean

interface EUser {
    /**
     * User id.
     * @return user id, unique
     */
    fun  getID(): String

    /**
     * Display name of user
     * @return display name
     */
    fun getNickname(): String?

    /**
     * Get user avatar file path.
     * @return avatar file path
     */

    fun getAvatar(): String?
}
