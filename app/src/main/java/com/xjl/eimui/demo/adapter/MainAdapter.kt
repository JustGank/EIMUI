package com.xjl.eimui.demo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.xjl.eimui.demo.R


class MainAdapter(var list: List<String>, activity: Activity) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var inflater: LayoutInflater
    init {
        inflater = activity.layoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = list[position]
        holder.button.setOnClickListener { v: View? ->
            if (clickListener != null) {
                clickListener!!.onClicked(v, position)
            }
        }
    }

    private var clickListener: ClickListener? = null


    fun setClickListener(clickListener: ClickListener?) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClicked(v: View?, position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var button: Button
        init {
            button = itemView.findViewById(R.id.button)
        }
    }
}
