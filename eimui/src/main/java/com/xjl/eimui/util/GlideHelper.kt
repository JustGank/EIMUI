package com.xjl.eimui.util;

import com.bumptech.glide.request.RequestOptions;
import com.xjl.eimui.R;

import java.util.HashMap;

public enum GlideHelper {

    INSTANCE;

    private RequestOptions onlyCenterCrop = null;

    public RequestOptions getOnCenterCrop() {
        if (onlyCenterCrop == null) {
            onlyCenterCrop = new RequestOptions();
            this.onlyCenterCrop.skipMemoryCache(false);
            this.onlyCenterCrop.centerCrop();
        }

        return onlyCenterCrop;
    }

    HashMap<Integer, RequestOptions> hashMap = new HashMap<>();

    public RequestOptions getErrorOptions(int res) {
        if (hashMap.get(res) == null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(false);
            requestOptions.error(R.mipmap.avatar_spiderman);
            hashMap.put(res, requestOptions);
        }
        return hashMap.get(res);
    }


}
