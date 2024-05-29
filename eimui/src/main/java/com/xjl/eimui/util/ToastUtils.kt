package com.xjl.eimui.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Created by h22272 on 2018/7/5.
 */
object ToastUtils {
    private val handler = Handler(Looper.getMainLooper())
    private var toast: Toast? = null
    private val synObj = Any()

    @JvmOverloads
    fun showMessage(context: Context, text: String?, duration: Int = Toast.LENGTH_SHORT) {
        handler.post {
            synchronized(synObj) {
                if (toast == null) {
                    toast = Toast.makeText(context, text, duration)
                } else {
                    toast!!.setText(text)
                    toast!!.duration = duration
                }
                toast!!.show()
            }
        }
    }

    @JvmOverloads
    fun showMessage(context: Context, resId: Int, duration: Int = Toast.LENGTH_SHORT) {
        handler.post {
            synchronized(synObj) {
                if (toast == null) {
                    toast = Toast.makeText(context, resId, duration)
                } else {
                    toast!!.setText(resId)
                    toast!!.duration = duration
                }
                toast!!.show()
            }
        }
    }

    fun showMessage(context: Context, resId: Int, vararg formatArgs: Any?) {
        handler.post {
            synchronized(synObj) {
                if (toast != null) {
                    toast!!.setText(context.getString(resId, *formatArgs))
                    toast!!.duration = Toast.LENGTH_SHORT
                } else {
                    toast = Toast.makeText(
                        context,
                        context.getString(resId, *formatArgs),
                        Toast.LENGTH_SHORT
                    )
                }
                toast!!.show()
            }
        }
    }
}