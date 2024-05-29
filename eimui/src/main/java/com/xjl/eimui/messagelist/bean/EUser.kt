package com.xjl.eimui.messagelist.bean

interface EUser {
    /**
     * User id.
     * @return user id, unique
     */
    val  id: String

    /**
     * Display name of user
     * @return display name
     */
    var nickname: String?

    /**
     * Get user avatar file path.
     * @return avatar file path
     */

    var avatar: String?
}
