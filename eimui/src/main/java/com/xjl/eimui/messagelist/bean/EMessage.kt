package com.xjl.eimui.messagelist.bean

interface EMessage {

    val msgID : String

    var mine: EUser

    var other: EUser

    var messageType : Int

    var header: String

    var content: String?

    var subContent: String?

    var mediaFilePath: String?

    var duration: Long

    var progress: Int

    var messageStatus: Int

     var isSelected: Boolean

    var isPlaying: Boolean

    val extras: HashMap<String, String>
}
