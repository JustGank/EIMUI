package com.xjl.eimui.inputbar.builder

import android.os.Parcel
import android.os.Parcelable
import com.xjl.eimui.R

class InputBarConfig() :Parcelable {
    var keybord_img_res = R.mipmap.chat_inputbar_keyboard

    var voice_img_res = R.mipmap.chat_inputbar_voice

    var left_img1_res=-1

    var left_img2_res = -1

    var left_img3_res = -1

    var right_img1_res = R.mipmap.chat_inputbar_more

    var right_img2_res = -1

    var right_img3_res = -1

    var inputBarBgResColor = R.color.main_color

    constructor(parcel: Parcel) : this() {
        keybord_img_res = parcel.readInt()
        voice_img_res = parcel.readInt()
        left_img1_res = parcel.readInt()
        left_img2_res = parcel.readInt()
        left_img3_res = parcel.readInt()
        right_img1_res = parcel.readInt()
        right_img2_res = parcel.readInt()
        right_img3_res = parcel.readInt()
        inputBarBgResColor = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(keybord_img_res)
        parcel.writeInt(voice_img_res)
        parcel.writeInt(left_img1_res)
        parcel.writeInt(left_img2_res)
        parcel.writeInt(left_img3_res)
        parcel.writeInt(right_img1_res)
        parcel.writeInt(right_img2_res)
        parcel.writeInt(right_img3_res)
        parcel.writeInt(inputBarBgResColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InputBarConfig> {
        override fun createFromParcel(parcel: Parcel): InputBarConfig {
            return InputBarConfig(parcel)
        }

        override fun newArray(size: Int): Array<InputBarConfig?> {
            return arrayOfNulls(size)
        }
    }
}
