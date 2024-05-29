package com.xjl.eimui.demo.bean

import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.EUser
import com.xjl.eimui.util.DadaFormatUtil

class IMessage(
    @JvmField val msgID: String,
    @JvmField var mine: EUser,
    @JvmField var other: EUser,
    @JvmField var messageType: Int
) : EMessage {

    override fun getMsgId(): String = msgID

    override fun setMine(user: EUser) {
        this.mine = user
    }

    override fun getMine(): EUser = mine

    override fun setOther(user: EUser) {
        this.other = user
    }

    override fun getOther(): EUser = other

    override fun getMessageType(): Int = messageType


    @JvmField
    var header: String = System.currentTimeMillis().toString()

    override fun getHeaderString(): String {
        return DadaFormatUtil.getTimeInterval(header)
    }

    @JvmField
    var content: String? = null
    override fun getContent(): String? = content

    @JvmField
    var subContent: String? = null
    override fun getSubContent(): String? = subContent

    @JvmField
    var mediaFilePath: String? = null
    override fun getMediaFilePath(): String? = mediaFilePath

    @JvmField
    var duration: Long = 0
    override fun getDuration(): Long {
        return if (duration > 0) duration else 1
    }

    @JvmField
    var progress = 0

    override fun setProgress(progress: Int) {
        this.progress = progress
    }

    override fun getProgress(): Int {
        return if (progress < 0) {
            0.also { progress = it }
        } else if (progress < 100) {
            progress
        } else {
            100
        }
    }

    @JvmField
    var messageStatus = 0
    override fun getMessageStatus(): Int = messageStatus

    override fun setMessageStatus(status: Int) {
        this.messageStatus = status
    }

    @JvmField
    var isSelected = false

    override fun isSelected(): Boolean = isSelected

    override fun setSelected(b: Boolean) {
        this.isSelected = b
    }

    @JvmField
    var isPlaying = false
    override fun isPlaying(): Boolean = isPlaying

    override fun setIsPlaying(b: Boolean) {
        this.isPlaying = b
    }

    @JvmField
    val extras: HashMap<String, String> = HashMap()
    override fun getExtras(): HashMap<String, String> = extras
}
