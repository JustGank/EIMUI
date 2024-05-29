package com.xjl.eimui.demo.bean

import java.io.File

object Constants {
    private var appCacheRoot:String=""
    fun init(appCacheRoot:String){
        Constants.appCacheRoot =appCacheRoot

    }

   fun getImgDir():String{
       return appCacheRoot +File.separator+"Images"
   }

    fun getAudioDir():String{
        return appCacheRoot +File.separator+"Audios"
    }

    fun getVideoDir():String{
        return appCacheRoot +File.separator+"Videos"
    }

}