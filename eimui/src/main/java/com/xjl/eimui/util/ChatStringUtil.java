package com.xjl.eimui.util;

import android.content.Context;

import com.xjl.eimui.R;


/**
 * Created by Administrator on 2019/3/27.
 */

public class ChatStringUtil {

    public static String getFileNameFromPath(String filePath) {
        String temp[] = filePath.split("/");
        return temp[temp.length - 1];
    }

    public static String getFileSDCardPath(String filePath) {
        return filePath.replace("=/storage/emulated/0/", "SDCard/");
    }

    public static String getFTPFileDirPath(String path) {
        String[] temp = path.split("/");
        int length = temp.length;
        return   temp[length - 2] + "/" + temp[length - 1];
    }

    public static String unreadMediaString(Context context,String filePath) {
        String endCase = filePath.substring(filePath.lastIndexOf("."));
        String chat_picture_message = context.getResources().getString(R.string.chat_picture_message);
        String chat_video_message = context.getResources().getString(R.string.chat_video_message);
        String chat_voice_message = context.getResources().getString(R.string.chat_voice_message);
        String chat_attach_message = context.getResources().getString(R.string.chat_attach_message);
        switch (endCase) {
            case ".jpeg":
            case ".png":
            case ".jpg":
                return "[" + chat_picture_message + "]";
            case ".mp4":
            case ".3gp":
                return "[" + chat_video_message + "]";
            case ".mp3":
                return "[" + chat_voice_message + "]";
            default:
                return "[" + chat_attach_message + "]";
        }
    }

    public static String str2HexStr(String str)
    {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }


}
