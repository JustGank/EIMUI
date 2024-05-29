package com.xjl.eimui.entry

import android.content.Context
import com.xjl.eimui.R

class InputBarEntry {
    var cantEmptyText: String = ""

    constructor(context: Context) {
        cantEmptyText = context.getString(R.string.cant_empty_text)
    }

    constructor(cantEmptyText: String) {
        this.cantEmptyText = cantEmptyText
    }

}