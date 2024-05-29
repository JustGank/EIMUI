package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xjl.eimui.R
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.EUser
import com.xjl.eimui.messagelist.bean.MessageStatus
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.messagelist.listener.OperationListener
import com.xjl.eimui.messagelist.widget.SendStateView
import com.xjl.eimui.util.GlideHelper

abstract class MessageViewHolderBase<MESSAGE : EMessage>(val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener,
    View.OnLongClickListener {
    //是否处于选中模式
    protected var isSelectedModel = false
    var header: TextView
    var is_select: ImageView
    var mine_avater: ImageView
    var mine_name: TextView
    var mine_content_container: RelativeLayout
    var mine_container: LinearLayout
    var state_view: SendStateView
    var other_avater: ImageView
    var other_name: TextView
    var other_content_container: RelativeLayout
    var other_container: RelativeLayout
    var chat_container: RelativeLayout
    var foot: TextView
    var operationListener: OperationListener<MESSAGE>? = null
    lateinit var data: MESSAGE
    var itemPosition = 0

    init {
        header = itemView.findViewById<View>(R.id.header) as TextView
        header.setOnClickListener(this)
        header.setOnLongClickListener(this)
        is_select = itemView.findViewById<View>(R.id.is_select) as ImageView
        is_select.setOnClickListener(this)
        is_select.setOnLongClickListener(this)
        mine_avater = itemView.findViewById<View>(R.id.mine_avater) as ImageView
        mine_avater.setOnClickListener(this)
        mine_avater.setOnLongClickListener(this)
        mine_name = itemView.findViewById<View>(R.id.mine_name) as TextView
        mine_name.setOnClickListener(this)
        mine_name.setOnLongClickListener(this)
        mine_content_container =
            itemView.findViewById<View>(R.id.mine_content_container) as RelativeLayout
        mine_container = itemView.findViewById<View>(R.id.mine_container) as LinearLayout
        state_view = itemView.findViewById<View>(R.id.state_view) as SendStateView
        state_view.setOnClickListener(this)
        state_view.setOnLongClickListener(this)
        other_avater = itemView.findViewById<View>(R.id.other_avater) as ImageView
        other_avater.setOnClickListener(this)
        other_avater.setOnLongClickListener(this)
        other_name = itemView.findViewById<View>(R.id.other_name) as TextView
        other_name.setOnClickListener(this@MessageViewHolderBase)
        other_name.setOnLongClickListener(this)
        other_content_container =
            itemView.findViewById<View>(R.id.other_content_container) as RelativeLayout
        other_container = itemView.findViewById<View>(R.id.other_container) as RelativeLayout
        chat_container = itemView.findViewById<View>(R.id.chat_container) as RelativeLayout
        foot = itemView.findViewById<View>(R.id.foot) as TextView
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.header -> {
                operationListener?.onHeaderClickListener(itemPosition, v, data)
            }

            R.id.mine_avater, R.id.mine_name -> {
                operationListener?.onMineInfoClickListener(itemPosition, v, data)
            }

            R.id.other_avater, R.id.other_name -> {
                operationListener?.onOtherInfoClickListener(itemPosition, v, data)
            }

            R.id.state_view -> {
                operationListener?.onStateViewClickListener(itemPosition, v, data)
            }

            else -> {
                operationListener?.onItemClickListener(itemPosition, v, data)
            }
        }
    }

    override fun onLongClick(v: View): Boolean {
        when (v.id) {
            R.id.header -> {
                operationListener?.onHeaderLongClickListener(itemPosition, v, data)
            }

            R.id.mine_avater, R.id.mine_name -> {
                operationListener?.onMineInfoLongClickListener(itemPosition, v, data)
            }

            R.id.other_avater, R.id.other_name -> {
                operationListener?.onOtherInfoLongClickListener(itemPosition, v, data)
            }

            else -> {
                operationListener?.onItemLongClickListener(itemPosition, v, data)
            }
        }
        return true
    }

    fun bindData(data: MESSAGE, isSelectedModel: Boolean, position: Int) {
        this.isSelectedModel = isSelectedModel
        this.data = data
        this.itemPosition = position

        //在选择模式下是否被选中
        setmIsSelected(data)
        //初始化聊天人员信息
        if (MessageType.isReceivedMessage(data.messageType)) {
            mine_container.visibility = View.GONE
            other_container.visibility = View.VISIBLE
            state_view.visibility = View.GONE
            setOtheInfo(data.other)
        } else {
            mine_container.visibility = View.VISIBLE
            other_container.visibility = View.GONE
            setMessageStatus(data.messageStatus)
            setMineInfo(data.mine)
        }
        //将容器交给子类实现
        mine_content_container.removeAllViews()
        other_content_container.removeAllViews()
        bindDateToChild(data, mine_content_container, other_content_container)
    }

    fun setHeader(data: MESSAGE, prevData: MESSAGE?, nextData: MESSAGE?) {
        if (nextData == null) {
            header.visibility = View.VISIBLE
            header.text = data.header
        } else {
            if (data.header == nextData.header) {
                header.visibility = View.GONE
            } else {
                header.visibility = View.VISIBLE
            }
            header.text = data.header
        }
    }

    fun setmIsSelected(data: MESSAGE) {
        is_select.visibility =
            if (isSelectedModel) View.VISIBLE else View.GONE
        is_select.setBackgroundResource(if (data.isSelected) R.mipmap.item_checked else R.mipmap.item_uncheck)
    }

    fun setMineInfo(user: EUser?) {
        if (user == null) {
            mine_avater.visibility = View.GONE
            mine_name.visibility = View.GONE
        } else {
            if (TextUtils.isEmpty(user.avatar) || user.avatar == "NULL") {
                mine_avater.visibility = View.GONE
            } else {
                mine_avater.visibility = View.VISIBLE
                GlideHelper.INSTANCE.getErrorOptions(R.mipmap.avatar_spiderman)?.let {
                    Glide.with(mine_avater.context)
                        .load(user.avatar)
                        .apply(it)
                        .into(mine_avater)
                }
            }
            if (TextUtils.isEmpty(user.nickname)) {
                mine_name.visibility = View.GONE
            } else {
                mine_name.visibility = View.VISIBLE
                mine_name.text = user.nickname
            }
        }
    }

    fun setOtheInfo(user: EUser?) {
        if (user == null) {
            other_avater.visibility = View.GONE
            other_name.visibility = View.GONE
        } else {
            if (TextUtils.isEmpty(user.avatar) || user.avatar.equals("NULL")) {
                other_avater.visibility = View.GONE
            } else {
                other_avater.visibility = View.VISIBLE
                GlideHelper.INSTANCE.getErrorOptions(R.mipmap.avatar_superman)?.let {
                    Glide.with(other_avater.context)
                        .load(user.avatar)
                        .apply(it)
                        .into(other_avater)
                }
            }
            if (TextUtils.isEmpty(user.nickname)) {
                other_name.visibility = View.GONE
            } else {
                other_name.visibility = View.VISIBLE
                other_name.text = user.nickname
            }
        }
    }

    fun setMessageStatus(status: Int) {
        if (status == MessageStatus.SEND_SUCCEED) {
            state_view.visibility = View.GONE
        } else if (status == MessageStatus.SEND_GOING) {
            state_view.visibility = View.VISIBLE
            state_view.setCurrentState(1)
        } else if (status == MessageStatus.SEND_FAILED) {
            state_view.setCurrentState(2)
        }
    }

    abstract fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    )
}
