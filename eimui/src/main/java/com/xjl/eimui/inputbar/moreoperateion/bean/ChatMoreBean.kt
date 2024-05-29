package com.xjl.eimui.inputbar.moreoperateion.bean;

import com.xjl.eimui.inputbar.moreoperateion.impl.Operation;

/**
 * Created by x33664 on 2019/1/8.
 */

public class ChatMoreBean {

    public int resId;

    public String title;

    public Operation operation;

    public ChatMoreBean(int resId, String title,Operation operation) {
        this.resId = resId;
        this.title = title;
        this.operation=operation;
    }

}
