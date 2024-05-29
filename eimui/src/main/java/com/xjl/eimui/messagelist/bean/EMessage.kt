package com.xjl.eimui.messagelist.bean

interface EMessage {

    fun getMsgId(): String

    fun setMine(user: EUser)

    fun getMine(): EUser

    fun setOther(user: EUser)

    fun getOther(): EUser

    fun getMessageType(): Int

    fun getHeaderString(): String?

    fun getContent(): String?

    fun getSubContent(): String?

    fun getMediaFilePath(): String?

    fun getDuration(): Long

    fun getProgress(): Int

    fun setProgress(progress: Int)

    fun getMessageStatus(): Int

    fun setMessageStatus(status: Int)

    fun isSelected(): Boolean

    fun setSelected(b: Boolean);

    fun isPlaying(): Boolean

    fun setIsPlaying(b: Boolean)

    fun getExtras(): HashMap<String, String>
}
