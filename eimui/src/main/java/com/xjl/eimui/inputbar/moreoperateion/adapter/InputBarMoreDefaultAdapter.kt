package com.xjl.eimui.inputbar.moreoperateion.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xjl.eimui.R
import com.xjl.eimui.inputbar.moreoperateion.bean.ChatMoreBean

class InputBarMoreDefaultAdapter(
    private val list: List<ChatMoreBean>,
    private val context: Context
) : RecyclerView.Adapter<InputBarMoreDefaultAdapter.ViewHolder>() {

    private val requestOptions: RequestOptions = RequestOptions().apply {
        skipMemoryCache(false)
        centerInside()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_inputbar_more, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatMoreBean = list[position]
        Glide.with(context)
            .load(chatMoreBean.resId)
            .apply(requestOptions)
            .into(holder.img)

        holder.title.visibility =
            if (TextUtils.isEmpty(chatMoreBean.title)) View.GONE else View.VISIBLE
        holder.title.text = chatMoreBean.title
        holder.container.setOnClickListener { view: View? ->
            view?.let { chatMoreBean.inputBarOperation.operate(it, position, context) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): ChatMoreBean {
        return list[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var title: TextView
        var container: LinearLayout

        init {
            img = itemView.findViewById(R.id.img)
            title = itemView.findViewById(R.id.title)
            container = itemView.findViewById(R.id.container)
        }
    }
}
