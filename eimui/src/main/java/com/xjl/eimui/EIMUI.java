package com.xjl.eimui;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public enum EIMUI {

    INSTANCE;

    private String saveFilePath = Environment.getExternalStorageDirectory().getPath();

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

    private Context context;

    public void init(Context context, String path) {
        this.context = context;
        setSaveFolderPath(path);
    }

    private void setSaveFolderPath(String path) {
        this.saveFilePath = path;
        new File(takePhotoSavePath).mkdirs();
        new File(takePhotoSavePath).mkdirs();
        new File(cacheDirPathAudio).mkdirs();
        new File(cacheDirPathCompress).mkdirs();
        new File(cacheDirPathFile).mkdirs();
    }

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

    public String getRecordVoicePath() {
        return cacheDirPathAudio;
    }

    public String getTakeVideoSavePath() {
        return takeVideoSavePath;
    }

    public Context getContext() {
        return context;
    }
}
