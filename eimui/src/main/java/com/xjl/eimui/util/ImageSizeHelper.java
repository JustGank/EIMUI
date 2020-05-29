package com.xjl.eimui.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.xjl.eimui.EIMUI;

public class ImageSizeHelper {

    private static final String TAG = "MediaSizeHelper";

    public static int[] getImageSize(int res) {
        int[] size = new int[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(EIMUI.INSTANCE.getContext().getResources(), res);

        //获取图片的宽高
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }

    public static int[] getImageSize(String filePath) {

        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//这个参数设置为true才有效，
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);//这里的bitmap是个空
        if (bmp == null) {
            Log.e(TAG, "通过options获取到的bitmap为空 ===");
        }

        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }


}
