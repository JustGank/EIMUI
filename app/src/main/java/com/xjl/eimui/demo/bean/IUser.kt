package com.xjl.eimui.demo.bean

import com.xjl.eimui.messagelist.bean.EUser

class IUser(
    @JvmField val id: String,
    @JvmField var nickname: String?,
    @JvmField var avatar: String?
) : EUser {

    override fun getID(): String = id

    override fun getNickname(): String? = nickname

    override fun getAvatar(): String? = avatar

}