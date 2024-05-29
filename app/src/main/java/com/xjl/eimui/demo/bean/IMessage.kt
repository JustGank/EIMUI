package com.xjl.eimui.demo.bean

import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.EUser
import com.xjl.eimui.util.DadaFormatUtil

class IMessage(
    override val msgID: String,
    override var mine: EUser,
    override var other: EUser,
    override var messageType: Int
) : EMessage {

    override var header: String = System.currentTimeMillis().toString()
        get() {
            return DadaFormatUtil.getTimeInterval(field)
        }
    override var content: String? = null
    override var subContent: String? = null
    override var mediaFilePath: String? = null
    override var duration: Long = 0
        get() {
            return if (field > 0) field else 1
        }
    override var progress = 0
        get() {
            return if (field < 0) {
                0.also { field = it }
            } else if (field < 100) {
                field
            } else {
                100
            }
        }
    override var messageStatus = 0
    override var isSelected = false
    override var isPlaying = false
    override val extras: HashMap<String, String> = HashMap()
}
