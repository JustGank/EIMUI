package com.xjl.eimui.messagelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xjl.eimui.R
import com.xjl.eimui.logger.Logger
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.holder.MessageViewHolderBase
import com.xjl.eimui.messagelist.holder.ViewHolderSendErrorMessage
import com.xjl.eimui.messagelist.listener.OperationListener
import com.xjl.eimui.messagelist.util.HolderClassManager

class EMessageAdapter<MESSAGE : EMessage>(
    val context: Context,
    var list: MutableList<MESSAGE>,
    val inflater: LayoutInflater = LayoutInflater.from(context)
) : RecyclerView.Adapter<MessageViewHolderBase<MESSAGE>>() {

    private val TAG = "MessageAdapter"
    private var isSelectedMode = false

    var operationListener: OperationListener<MESSAGE>? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageViewHolderBase<MESSAGE> {
        val holder = getHolder(parent, HolderClassManager.getViewHolderClass(viewType))
        Logger.i("$TAG onCreateViewHolder class" + holder::class.java.simpleName)
        operationListener?.let { holder.operationListener = it }

        return holder
    }

    override fun onBindViewHolder(holder: MessageViewHolderBase<MESSAGE>, position: Int) {
        val data = list[position]
        //由于头部信息和上下两条数据可能有关系
        if (position == 0) {
            if (itemCount > 1) {
                holder.setHeader(data, null, list[1])
            } else {
                holder.setHeader(data, null, null)
            }
        } else {
            if (itemCount < 3) {
                holder.setHeader(data, list[position - 1], null)
            } else {
                if (position + 1 < list.size && list[position + 1] != null) {
                    holder.setHeader(data, list[position - 1], list[position + 1])
                } else {
                    holder.setHeader(data, list[position - 1], null)
                }
            }
        }
        holder.bindData(list[position], isSelectedMode, position)
        holder.foot.visibility = View.GONE
    }

    /**
     * 数据操作相关方法
     */
    fun replaceList(list: List<MESSAGE>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(message: MESSAGE) {
        list.add(message)
        notifyDataSetChanged()
    }

    fun insertItem(message: MESSAGE) {
        list.add(0, message)
        notifyDataSetChanged()
    }

    fun getMessageId(position: Int): String {
        return list[position].getMsgId()
    }

    fun deleteItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updataProgress(msgId: String, progress: Int) {
        for (i in list.indices) {
            val message = list[i]
            if (message.getMsgId() == msgId) {
                message.setProgress(progress)
                notifyItemChanged(i)
                break
            }
        }
    }

    /**
     * 更改模式
     */
    fun setSelectedMode(isSelectedMode: Boolean) {
        this.isSelectedMode = isSelectedMode
        notifyDataSetChanged()
    }

    fun isSelectedMode(): Boolean {
        return isSelectedMode
    }

    fun updateItemSelected(position: Int) {
        list[position].setSelected(!list[position].isSelected())
        notifyItemChanged(position)
    }

    fun updateMessageState(msgId: String?, newState: Int) {
        for (i in list.indices) {
            if (list[i].getMsgId() == msgId) {
                list[i].setMessageStatus(newState)
                notifyItemChanged(i)
                break
            }
        }
    }

    fun updatePlaying(position: Int, playing: Boolean) {
        getItem(position).setIsPlaying(playing)
        notifyItemChanged(position)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getMessageType()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): EMessage {
        return list[position]
    }

    fun <HOLDER : MessageViewHolderBase<out EMessage>> getHolder(
        parent: ViewGroup?,
        holderClass: Class<HOLDER>
    ): MessageViewHolderBase<MESSAGE> {
        val view = inflater.inflate(R.layout.item_messagelist_container, parent, false)
        var holder: MessageViewHolderBase<MESSAGE>? = null
        try {
            val constructor = holderClass.getDeclaredConstructor(
                Context::class.java, View::class.java
            )
            holder = constructor.newInstance(context, view) as MessageViewHolderBase<MESSAGE>
        } catch (e: Exception) {
            Logger.e("$TAG getHolder Exception=" + e.message)
        }
        if (holder == null) {
            holder = ViewHolderSendErrorMessage<MESSAGE>(context, view)
        }
        return holder
    }

}
