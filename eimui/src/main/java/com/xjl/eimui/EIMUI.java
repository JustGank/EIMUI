package com.xjl.eimui;

import android.os.Environment;

import java.io.File;

public enum EIMUI {

    INSTANCE;

    private String saveFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();

    //图片缓存的位置
    private String takePhotoSavePath = saveFilePath + File.separator + "Images";
    //视频缓存的位置
    private String takeVideoSavePath = saveFilePath + File.separator + "Videos";
    //音频缓存的位置
    private String cacheDirPathAudio = saveFilePath + File.separator + "Audios";
    //压缩图片的位置
    private String cacheDirPathCompress = saveFilePath + File.separator + "CompressedImages";
    //文件的位置
    private String cacheDirPathFile = saveFilePath + File.separator + "Files";

    public EIMUI setTakePhotoSavePath(String path) {
        this.takePhotoSavePath = path;
        return INSTANCE;
    }

    public String getTakePhotoSavePath() {
        return takePhotoSavePath;
    }

    public EIMUI setTakeVideoSavePath(String path) {
        this.takeVideoSavePath = path;
        return INSTANCE;
    }

    public String getTakeVideoSavePath() {
        return takeVideoSavePath;
    }


}
