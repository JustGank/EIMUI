package com.xjl.eimui.inputbar

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageInputbarBinding
import com.xjl.eimui.entry.InputBarEntry
import com.xjl.eimui.inputbar.builder.InputBarConfig
import com.xjl.eimui.util.ToastUtils.showMessage


open class InputBar : LinearLayout, View.OnClickListener {

    var isCurrentInputStateIsKey = true
    lateinit var binding: ViewMessageInputbarBinding
    lateinit var inputBarEntry: InputBarEntry
    var inputBarConfig: InputBarConfig = InputBarConfig()
        set(inputBarConfig) {
            field = inputBarConfig
            binding.apply {
                initImageView(field.left_img1_res, leftImg1)
                initImageView(field.left_img2_res, leftImg2)
                initImageView(field.left_img3_res, leftImg3)
                initImageView(field.right_img1_res, rightImg1)
                initImageView(field.right_img2_res, rightImg2)
                initImageView(field.right_img3_res, rightImg3)
                inputbarContainer.setBackgroundColor(resources.getColor(inputBarConfig.inputBarBgResColor))
            }
        }

    constructor(context: Context?) : super(context) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        context?.let {
            initView(it, attrs, 0, 0)
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        context?.let {
            initView(it, attrs, defStyleAttr, 0)
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        context?.let {
            initView(it, attrs, defStyleAttr, defStyleRes)
        }
    }

    private var KeyVoiceSwitchViewId = R.id.left_img1

    /**
     * LeftImg1作为预留逻辑项，默认实现切换 键盘输入/语音输入
     */
    private fun initView(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.InputBar, defStyleAttr, defStyleRes)
        KeyVoiceSwitchViewId =
            a.getResourceId(R.styleable.InputBar_keyVoiceSwitchViewId, R.id.left_img1)
        a.recycle()

        inputBarEntry = InputBarEntry(context)
        binding = ViewMessageInputbarBinding.inflate(LayoutInflater.from(context),this,true)
        binding.apply {
            leftImg1.setOnClickListener(this@InputBar)
            leftImg2.setOnClickListener(this@InputBar)
            leftImg3.setOnClickListener(this@InputBar)
            editview.setOnKeyListener(OnKeyListener { v, keyCode, event ->
                if (keyCode == 66 && event.action == 1) {
                    val content = editview.text.toString()
                    if (TextUtils.isEmpty(content.trim { it <= ' ' })) {
                        showMessage(context, inputBarEntry.cantEmptyText)
                        return@OnKeyListener false
                    }
                    if (onItemClickListener != null) {
                        onItemClickListener!!.onSendClicked(content)
                    }
                }
                false
            })
            rightImg1.setOnClickListener(this@InputBar)
            rightImg2.setOnClickListener(this@InputBar)
            rightImg3.setOnClickListener(this@InputBar)
        }
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == KeyVoiceSwitchViewId) {
            switchKeyVoiceState(v)
        }
        if (id == R.id.left_img1) {
            onItemClickListener?.onLeftImg1Clicked(binding.leftImg1)
        } else if (id == R.id.left_img2) {
            onItemClickListener?.onLeftImg2Clicked(binding.leftImg2)
        } else if (id == R.id.left_img3) {
            onItemClickListener?.onLeftImg3Clicked(binding.leftImg3)
        } else if (id == R.id.right_img1) {
            onItemClickListener?.onRightImg1Clicked(binding.rightImg1)
        } else if (id == R.id.right_img2) {
            onItemClickListener?.onRightImg2Clicked(binding.rightImg2)
        } else if (id == R.id.right_img3) {
            onItemClickListener?.onRightImg3Clicked(binding.rightImg3)
        }
    }

    fun switchKeyVoiceState(view: View) {
        isCurrentInputStateIsKey = !isCurrentInputStateIsKey
        if (isCurrentInputStateIsKey) {
            binding.editview.visibility = View.VISIBLE
            binding.pressTalk.visibility = View.GONE
            view.setBackgroundResource(inputBarConfig.voice_img_res)
        } else {
            binding.editview.visibility = View.GONE
            binding.pressTalk.visibility = View.VISIBLE
            view.setBackgroundResource(inputBarConfig.keybord_img_res)
        }
        view.visibility = View.VISIBLE
    }


    private fun initImageView(res: Int, imageView: ImageView) {
        if (res == -1) {
            imageView.visibility = GONE
        } else {
            imageView.visibility = VISIBLE
            imageView.setBackgroundResource(res)
        }

        if (imageView.id == KeyVoiceSwitchViewId) {
            if (inputBarConfig.keybord_img_res == -1 && inputBarConfig.voice_img_res == -1) {
                imageView.visibility = View.GONE
            } else {
                imageView.visibility = View.VISIBLE
                imageView.setBackgroundResource(if (isCurrentInputStateIsKey) inputBarConfig.voice_img_res else inputBarConfig.keybord_img_res)
            }
        }
    }

    fun getLeftImg1():ImageView{
        return binding.leftImg1
    }

    fun getLeftImg2():ImageView{
        return binding.leftImg2
    }

    fun getLeftImg3():ImageView{
        return binding.leftImg3
    }

    fun getEdittext():EditText{
        return binding.editview
    }

    fun getPressTalk():TextView{
        return binding.pressTalk
    }


    fun getRightImg1():ImageView{
        return binding.rightImg1
    }

    fun getRightImg2():ImageView{
        return binding.rightImg2
    }

    fun getRightImg3():ImageView{
        return binding.rightImg3
    }

    fun getMorePanel():RecyclerView{
        return binding.morePanel
    }

    fun setPressTalkOnTouchListener(onTouchListener: OnTouchListener?) {
        binding.pressTalk.setOnTouchListener(onTouchListener)
    }

    fun setEditviewFocusListener(onFocusChangeListener: OnFocusChangeListener?) {
        binding.editview.onFocusChangeListener = onFocusChangeListener
    }

    /**
     * 由于控件较多 采用类的形式 减少因使用接口而暴露所有方法
     */
    var onItemClickListener: OnItemClickListener? = null

    open class OnItemClickListener {
        open fun onSendClicked(content: String) {}
        open fun onLeftImg1Clicked(img: ImageView) {}
        open fun onLeftImg2Clicked(img: ImageView) {}
        open fun onLeftImg3Clicked(img: ImageView) {}
        open fun onRightImg1Clicked(img: ImageView) {}
        open fun onRightImg2Clicked(img: ImageView) {}
        open fun onRightImg3Clicked(img: ImageView) {}
    }
}
