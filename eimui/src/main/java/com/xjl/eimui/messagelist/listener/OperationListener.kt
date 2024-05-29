package com.xjl.eimui.messagelist.listener

import android.view.View
import com.xjl.eimui.messagelist.bean.EMessage

open class OperationListener<MESSAGE : EMessage> {
    fun onHeaderClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onHeaderLongClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onOtherInfoClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onOtherInfoLongClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onMineInfoClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onMineInfoLongClickListener(position: Int, v: View, data: MESSAGE) {}
    open fun onItemClickListener(position: Int, v: View, data: MESSAGE) {}
    fun onItemLongClickListener(position: Int, v: View, data: MESSAGE) {}
    open fun onStateViewClickListener(position: Int, v: View, data: MESSAGE) {}
}
