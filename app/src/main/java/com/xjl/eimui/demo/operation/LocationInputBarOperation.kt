package com.xjl.eimui.demo.operation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.xjl.eimui.inputbar.moreoperateion.impl.InputBarOperation

class LocationInputBarOperation : InputBarOperation {
    override fun operate(v: View, position: Int, context: Context) {
        val uri = Uri.parse("https://www.amap.com/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}
