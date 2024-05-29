package com.xjl.eimui.messagelist.listener;

import android.view.View;

import com.xjl.eimui.messagelist.bean.EMessage;

public class OperationListener<MESSAGE extends EMessage> {

    public void onHeaderClickListener(int position, View v, MESSAGE data){}

    public void onHeaderLongClickListener(int position, View v, MESSAGE data){}

    public void onOtherInfoClickListener(int position, View v, MESSAGE data){ }

    public void onOtherInfoLongClickListener(int position, View v, MESSAGE data){ }

    public void onMineInfoClickListener(int position, View v, MESSAGE data){}

    public void onMineInfoLongClickListener(int position, View v, MESSAGE data){}

    public void onItemClickListener(int position, View v, MESSAGE data){};

    public void onItemLongClickListener(int position, View v, MESSAGE data){};

    public void onStateViewClickListener(int position, View v, MESSAGE data){};

}
