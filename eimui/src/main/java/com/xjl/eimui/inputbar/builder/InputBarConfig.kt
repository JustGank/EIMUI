package com.xjl.eimui.inputbar.builder;

import com.xjl.eimui.R;

public class InputBarBuilder {

    private int left_img1_keybord = R.mipmap.chat_inputbar_keyboard;

    private int left_img1_voice = R.mipmap.chat_inputbar_voice;

    private int left_img2_res = -1;

    private int left_img3_res = -1;

    private int right_img1_res = R.mipmap.chat_inputbar_more;

    private int right_img2_res = -1;

    private int right_img3_res = -1;

    private int inputBarBgResColor=R.color.main_color;

    private InputBarBuilder(){}

    public static InputBarBuilder getNewInstance() {
        return new InputBarBuilder();
    }

    public int getLeft_img1_keybord() {
        return left_img1_keybord;
    }

    public InputBarBuilder setLeft_img1_keybord(int left_img1_keybord) {
        this.left_img1_keybord = left_img1_keybord;
        return this;
    }

    public int getLeft_img1_voice() {
        return left_img1_voice;
    }

    public InputBarBuilder setLeft_img1_voice(int left_img1_voice) {
        this.left_img1_voice = left_img1_voice;
        return this;
    }

    public int getLeft_img2_res() {
        return left_img2_res;
    }

    public InputBarBuilder setLeft_img2_res(int left_img2_res) {
        this.left_img2_res = left_img2_res;
        return this;
    }

    public int getLeft_img3_res() {
        return left_img3_res;
    }

    public InputBarBuilder setLeft_img3_res(int left_img3_res) {
        this.left_img3_res = left_img3_res;
        return this;
    }

    public int getRight_img1_res() {
        return right_img1_res;
    }

    public InputBarBuilder setRight_img1_res(int right_img1_res) {
        this.right_img1_res = right_img1_res;
        return this;
    }

    public int getRight_img2_res() {
        return right_img2_res;
    }

    public InputBarBuilder setRight_img2_res(int right_img2_res) {
        this.right_img2_res = right_img2_res;
        return this;
    }

    public int getRight_img3_res() {
        return right_img3_res;
    }

    public InputBarBuilder setRight_img3_res(int right_img3_res) {
        this.right_img3_res = right_img3_res;
        return this;
    }

    public int getInputBarBgResColor() {
        return inputBarBgResColor;
    }

    public InputBarBuilder setInputBarBgResColor(int inputBarBgResColor) {
        this.inputBarBgResColor = inputBarBgResColor;
        return this;
    }



}
