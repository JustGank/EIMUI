package com.xjl.eimui.util

import com.bumptech.glide.request.RequestOptions
import com.xjl.eimui.R

enum class GlideHelper {
    INSTANCE;

    private var onlyCenterCrop: RequestOptions? = null
    val onCenterCrop: RequestOptions
        get() {
            if (onlyCenterCrop == null) {
                onlyCenterCrop = RequestOptions().apply{
                    skipMemoryCache(false)
                    centerCrop()
                }
            }
            return onlyCenterCrop!!
        }
    var hashMap = HashMap<Int, RequestOptions?>()
    fun getErrorOptions(res: Int): RequestOptions? {
        if (hashMap[res] == null) {
            val requestOptions = RequestOptions()
            requestOptions.skipMemoryCache(false)
            requestOptions.error(R.mipmap.avatar_spiderman)
            hashMap[res] = requestOptions
        }
        return hashMap[res]
    }
}
