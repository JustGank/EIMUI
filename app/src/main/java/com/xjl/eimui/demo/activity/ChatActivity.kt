package com.xjl.eimui.demo.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.xjl.eimui.demo.R
import com.xjl.eimui.demo.databinding.ActivityChatBinding
import com.xjl.eimui.inputbar.InputBar
import com.xjl.eimui.inputbar.builder.InputBarConfig
import com.xjl.eimui.inputbar.moreoperateion.adapter.InputBarMoreDefaultAdapter
import com.xjl.eimui.inputbar.moreoperateion.bean.ChatMoreBean
import com.xjl.eimui.inputbar.moreoperateion.impl.InputBarOperation
import com.xjl.eimui.inputbar.recordstate.RecordStateListener
import com.xjl.eimui.inputbar.recordstate.RecordTouchListener
import com.xjl.eimui.logger.Logger
import com.xjl.eimui.messagelist.adapter.EMessageAdapter
import com.xjl.eimui.messagelist.bean.MessageStatus
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.messagelist.listener.OperationListener
import com.xjl.eimui.util.ToastUtils.showMessage
import com.xjl.eimui.demo.bean.Constants
import com.xjl.eimui.demo.bean.IMessage
import com.xjl.eimui.demo.bean.TestDataFactory
import com.xjl.eimui.demo.bean.TestDataFactory.getIMessage
import com.xjl.eimui.demo.bean.TestDataFactory.getTestData
import com.xjl.eimui.demo.operation.LocationInputBarOperation
import com.xjl.emedia.bean.MediaPickerRequestBean
import com.xjl.emedia.bean.MediaRecordRequestBean
import com.xjl.emedia.bean.PickerType
import com.xjl.emedia.bean.RecordQuality
import com.xjl.emedia.contract.AlbumPickerContract
import com.xjl.emedia.contract.TakeFileContract
import com.xjl.emedia.contract.TakePhotoContract
import com.xjl.emedia.contract.TakeVideoContract
import com.xjl.emedia.utils.IntentUtil
import com.xjl.emp3recorder.mp3record.MP3Recorder
import com.xjl.emp3recorder.utils.MediaPlayerHepler
import java.io.File
import java.io.IOException

class ChatActivity : AppCompatActivity() {

    private val TAG = "InputTestActivity"

    private lateinit var adapter: EMessageAdapter<IMessage>
    private lateinit var binding: ActivityChatBinding
    private lateinit var recordTouchListener: RecordTouchListener
    private lateinit var inputBarMoreDefaultAdapter: InputBarMoreDefaultAdapter

    private lateinit var mp3Recorder: MP3Recorder
    private lateinit var mediaPlayerHepler: MediaPlayerHepler

    private var currentAudioFile: File? = null
    var messageId = 0
    private val MAX_FILE_LENGTH_M = 20

    private var photoLauncher: ActivityResultLauncher<String>? = null
    private var videoLauncher: ActivityResultLauncher<MediaRecordRequestBean>? = null
    private var fileLauncher: ActivityResultLauncher<String?>? = null
    private var mediaLauncher: ActivityResultLauncher<MediaPickerRequestBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mp3Recorder = MP3Recorder()
        mp3Recorder.setOnRecordListener(onRecordListener)
        mediaPlayerHepler = MediaPlayerHepler()
        mediaPlayerHepler.completeListener = completionListener

        binding.apply {
            adapter = EMessageAdapter<IMessage>(this@ChatActivity, ArrayList<IMessage>())
            adapter.replaceList(getTestData(intent.getIntExtra("index", 1)))
            recycler.setAdapter(adapter)
            adapter.operationListener = operationListener

            //初始化inputBar的更多操作
            val chatMoreBeans: MutableList<ChatMoreBean> = ArrayList()

            mediaLauncher = registerForActivityResult(AlbumPickerContract()) { tempList ->
                if (tempList?.isNotEmpty() == true) {
                    sendMediaMessage(tempList[0].mediaFilePath, MessageType.SEND_IMAGE)
                }

            }
            //使用默认Operation
            chatMoreBeans.add(
                ChatMoreBean(
                    R.mipmap.chat_pick_pic,
                    getString(R.string.chat_pick_pic),
                    object : InputBarOperation {
                        override fun operate(v: View, position: Int, context: Context) {
                            mediaLauncher?.launch(MediaPickerRequestBean().apply {
                                max_chose_num = 1
                                pickerType = PickerType.PHOTO_VIDEO
                                openBottomMoreOperate = true
                                overSizeVisible = true
                                rowNum = 5
                            })
                            binding.inputbar.getMorePanel().visibility = View.GONE
                        }
                    }
                )
            )

            photoLauncher = registerForActivityResult(TakePhotoContract()) { filePath ->
                filePath?.let { sendMediaMessage(it, MessageType.SEND_IMAGE) }
            }

            chatMoreBeans.add(
                ChatMoreBean(
                    R.mipmap.chat_take_photo,
                    getString(R.string.chat_take_photo),
                    object : InputBarOperation {
                        override fun operate(v: View, position: Int, context: Context) {
                            photoLauncher?.launch(Constants.getImgDir())
                            binding.inputbar.getMorePanel().visibility = View.GONE
                        }
                    }
                )
            )

            videoLauncher = registerForActivityResult(TakeVideoContract()) { filePath ->
                filePath?.let { sendMediaMessage(it, MessageType.SEND_VIDEO) }
            }

            chatMoreBeans.add(
                ChatMoreBean(
                    R.mipmap.chat_take_video,
                    getString(R.string.chat_take_video),
                    object : InputBarOperation {
                        override fun operate(v: View, position: Int, context: Context) {
                            videoLauncher?.launch(MediaRecordRequestBean().apply {
                                recordQuality = RecordQuality.ALL
                                limitTime = 30
                                saveDirPath = Constants.getVideoDir()
                            })
                            binding.inputbar.getMorePanel().visibility = View.GONE
                        }
                    }
                )
            )

            fileLauncher = registerForActivityResult(TakeFileContract()) { filePath ->
                filePath?.let { sendMediaMessage(it, MessageType.SEND_FILE) }
            }

            chatMoreBeans.add(
                ChatMoreBean(
                    R.mipmap.chat_pick_file,
                    getString(R.string.chat_file),
                    object : InputBarOperation {
                        override fun operate(v: View, position: Int, context: Context) {
                            fileLauncher?.launch("*/*")
                            binding.inputbar.getMorePanel().visibility = View.GONE
                        }
                    }
                )
            )
            //自定义Operation
            chatMoreBeans.add(
                ChatMoreBean(
                    R.mipmap.chat_location,
                    getString(R.string.chat_location),
                    LocationInputBarOperation()
                )
            )

            //设置更多操作
            inputBarMoreDefaultAdapter =
                InputBarMoreDefaultAdapter(chatMoreBeans, this@ChatActivity)
            binding.inputbar.apply {
                getMorePanel().setLayoutManager(GridLayoutManager(this@ChatActivity, 4))
                getMorePanel().setAdapter(inputBarMoreDefaultAdapter)

                //设置InputBar 子控件监听 增加一个自定义控件
                inputBarConfig = InputBarConfig().apply {
                    left_img1_res=R.mipmap.chat_hi
                    right_img1_res=R.mipmap.chat_inputbar_more
                    right_img2_res = R.mipmap.chat_inputbar_template
                    inputBarBgResColor = R.color.colorPrimary
                }
                onItemClickListener = this@ChatActivity.onItemClickListener

                //设置长按说话的动画 和回调监听者
                recordTouchListener =
                    RecordTouchListener(this@ChatActivity, recordstateView, recordStateListener)
                //将TouchListener 和Inputbar相绑定
                setPressTalkOnTouchListener(recordTouchListener)
            }
        }
    }


    var operationListener: OperationListener<IMessage> = object : OperationListener<IMessage>() {
        override fun onItemClickListener(position: Int, v: View, data: IMessage) {
            when (data.messageType) {
                MessageType.RECEIVE_TEXT, MessageType.SEND_TEXT ->{
                    Logger.i("$TAG onItemClickListener position=" + position + " data=" + data.content)
                }

                MessageType.RECEIVE_IMAGE , MessageType.SEND_IMAGE -> {
                    Log.e(
                        TAG,
                        "onItemClickListener position=" + position + " data=" + data.mediaFilePath
                    )
                data.mediaFilePath?.let { openFile(it) }
                }

                MessageType.RECEIVE_VIDEO, MessageType.SEND_VIDEO -> {
                    Log.e(
                        TAG,
                        "onItemClickListener position=" + position + " data=" + data.messageType
                    )
                    //调用此方法时需要文件已正常存在!
                    data.mediaFilePath?.let { openFile(it) }
                }

                MessageType.RECEIVE_VOICE, MessageType.SEND_VOICE -> if (TextUtils.isEmpty(data.mediaFilePath)) {
                    showMessage(this@ChatActivity, "录音文件为空")
                } else {

                    if (lastPlayPosition == position) {
                        if (adapter.getItem(position).isPlaying()) {
                            adapter.updatePlaying(position, false)
                            mediaPlayerHepler.stop()
                        } else {
                            adapter.updatePlaying(position, true)
                            mediaPlayerHepler.start(adapter.getItem(position).getMediaFilePath())
                        }
                    } else {
                        if (lastPlayPosition > -1) {
                            adapter.updatePlaying(lastPlayPosition, false)
                            mediaPlayerHepler.stop()
                        }
                        lastPlayPosition = position
                        adapter.updatePlaying(position, true)
                        mediaPlayerHepler.start(adapter.getItem(position).getMediaFilePath())
                    }
                }

                MessageType.RECEIVE_LOCATION, MessageType.SEND_LOCATION -> Log.e(
                    TAG,
                    "onItemClickListener position=" + position + " data=" + data.content
                )

                MessageType.RECEIVE_FILE, MessageType.SEND_FILE -> if (v.id == R.id.item_chat_file_download) {
                    adapter.getItem(position).setProgress(adapter.getItem(position).getProgress()+5)
                    adapter.notifyItemChanged(position)
                } else if (v.id == R.id.item_chat_file_container) {
                    Log.e(
                        TAG,
                        "onItemClickListener position=" + position + " current Progress=" + data.progress
                    )
                }
            }
        }

        override fun onStateViewClickListener(position: Int, v: View, data: IMessage) {
            Log.e(
                TAG,
                "onStateViewClickListener position=" + position + " data=" + data.messageStatus
            )
        }
    }


    private val onItemClickListener: InputBar.OnItemClickListener =
        object : InputBar.OnItemClickListener() {
            override fun onSendClicked(content: String) {
                super.onSendClicked(content)
                val iMessage = IMessage(
                    (messageId++).toString(),
                    TestDataFactory.mine,
                    TestDataFactory.other,
                    MessageType.SEND_TEXT
                )
                iMessage.content = content
                iMessage.messageStatus=MessageStatus.SEND_GOING
                adapter.insertItem(iMessage)
                binding.inputbar.getEdittext().setText("")
            }

            override fun onLeftImg1Clicked(img: ImageView) {
                super.onLeftImg1Clicked(img)
                showMessage(this@ChatActivity, "您点击了Hi")
            }


            override fun onRightImg1Clicked(img: ImageView) {
                super.onRightImg1Clicked(img)
                binding.inputbar.getMorePanel().visibility=View.VISIBLE
            }

            override fun onRightImg2Clicked(img: ImageView) {
                super.onRightImg2Clicked(img)
                showMessage(this@ChatActivity, "您点击了自定义控件按钮")
            }
        }
    private var isCancelRecord = false
    private var lastPlayPosition = -1
    var recordStateListener: RecordStateListener = object : RecordStateListener {
        override fun onRecordStateChange(currentState: Int) {
            when (currentState) {
                RecordStateListener.START_RECORD -> {
                    isCancelRecord = false
                    currentAudioFile =
                        File(Constants.getAudioDir() + File.separator + System.currentTimeMillis() + ".mp3")
                    try {

                        if (ActivityCompat.checkSelfPermission(
                                this@ChatActivity,
                                Manifest.permission.RECORD_AUDIO
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return
                        }
                        mp3Recorder.start((60 * 1000).toLong(), currentAudioFile!!)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                RecordStateListener.CANCEL_RECORD -> {
                    isCancelRecord = true
                    mp3Recorder.stop()
                }

                RecordStateListener.RECORD_FINISH -> {
                    isCancelRecord = false
                    mp3Recorder.stop()
                }
            }
        }

        override fun onFrequenceClick(downTime: Long, upTime: Long) {
            showMessage(this@ChatActivity, "您的点击过快")
        }
    }

    /**
     * 音频组件回调
     */
    private val HANDLER_RESET_RECORD_VIEW = 107
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                HANDLER_RESET_RECORD_VIEW -> {
                    binding.apply {
                        inputbar.clearFocus()
                        recordstateView.dismiss()
                    }
                }
            }
        }
    }

    /**
     * 音频组件回调
     */
    var onRecordListener: MP3Recorder.OnRecordListener = object : MP3Recorder.OnRecordListener {
        override fun onStart() {}
        override fun onStop(file: File, l: Long) {
            handler.sendEmptyMessage(HANDLER_RESET_RECORD_VIEW)
            if (isCancelRecord) {
                file.delete()
            } else {
                if (l < 1000 || file.length() < 1536) {
                    showMessage(this@ChatActivity, getResources().getString(R.string.short_time))
                    file.delete()
                } else {
                    runOnUiThread {
                        val iMessage = IMessage(
                            (++messageId).toString(),
                            TestDataFactory.mine,
                            TestDataFactory.other,
                            MessageType.SEND_VOICE
                        )
                        iMessage.mediaFilePath = file.path
                        iMessage.duration = l / 1000
                        iMessage.messageStatus=MessageStatus.SEND_GOING
                        adapter.insertItem(iMessage)
                    }
                }
            }
        }

        override fun onRecording(mVolumeDb: Int, mVolume: Int) {
            binding.recordstateView.refreshCurrenDB(mVolumeDb)
        }
    }
    var completionListener = OnCompletionListener {
        mediaPlayerHepler.stop()
        adapter.updatePlaying(lastPlayPosition, false)
    }

    private fun sendMediaMessage(filePath: String, type: Int) {
        val iMessage = getIMessage(type)
        iMessage.mediaFilePath = filePath
        iMessage.messageStatus=MessageStatus.SEND_GOING
        adapter.insertItem(iMessage)
    }

    private fun sendMediaMessage(file: File, type: Int) {
        val iMessage = getIMessage(type)
        iMessage.mediaFilePath = file.absolutePath
        iMessage.content = file.name
        iMessage.messageStatus=MessageStatus.SEND_GOING
        adapter.insertItem(iMessage)
    }

    private fun openFile(path: String) {
        if (File(path).exists()) {
            IntentUtil.openMedia(this, path)
        } else {
            showMessage(this, R.string.invalid_file)
        }
    }


}
