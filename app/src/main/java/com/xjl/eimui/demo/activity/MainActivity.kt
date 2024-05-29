package com.xjl.eimui.demo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjl.eimui.demo.databinding.ActivityMainBinding
import com.xjl.eimui.demo.adapter.MainAdapter
import com.xjl.eimui.demo.adapter.MainAdapter.ClickListener
import com.xjl.eimui.demo.bean.Constants
import com.xjl.emedia.logger.Logger
import com.xjl.emedia.utils.EMediaPermissionUtil
import java.io.File

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    lateinit var adapter: MainAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val deniedPermissions = permissions.filterNot { it.value }
                Logger.i("$TAG onCreate deniedPermissions:$deniedPermissions")
                if (deniedPermissions.isNotEmpty()) {
                    permissionLauncher.launch(EMediaPermissionUtil.getAllNotGrantedPermissions(this@MainActivity))
                }
            }
        permissionLauncher.launch(EMediaPermissionUtil.getAllNotGrantedPermissions(this@MainActivity))

        externalCacheDir?.absolutePath?.let { Constants.init(it) }
        File(Constants.getImgDir()).let { if (!it.exists()) it.mkdirs() }
        File(Constants.getAudioDir()).let { if (!it.exists()) it.mkdirs() }
        File(Constants.getAudioDir()).let { if (!it.exists()) it.mkdirs() }


        val list: MutableList<String> = ArrayList()
        list.add("Muilt Message Demo")
        list.add("Text Message Demo")
        list.add("Image Message Demo")
        list.add("Video Message Demo")
        list.add("Voice Message Demo")
        list.add("Location Message Demo")
        list.add("File Message Demo")
        list.add("Error Message Demo")

        binding.apply {
            recycler.setLayoutManager(LinearLayoutManager(this@MainActivity))
            adapter = MainAdapter(list, this@MainActivity)
            recycler.setAdapter(adapter)
            adapter.setClickListener(object : ClickListener {
                override fun onClicked(v: View?, position: Int) {
                    val intent = Intent(this@MainActivity, ChatActivity::class.java)
                    intent.putExtra("index", position)
                    startActivity(intent)
                }
            })
        }


    }


}
