package com.xjl.eimui_demo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xjl.eimui.EIMUI;
import com.xjl.eimui_demo.R;
import com.xjl.eimui_demo.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;

    private MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 1001);
        }

        EIMUI.INSTANCE.setSaveFolderPath("EIMUI");

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        List<String> list = new ArrayList<>();
        list.add("Muilt Message Demo");
        list.add("Text Message Demo");
        list.add("Image Message Demo");
        list.add("Video Message Demo");
        list.add("Voice Message Demo");
        list.add("Location Message Demo");
        list.add("File Message Demo");
        list.add("Error Message Demo");

        adapter = new MainAdapter(list, MainActivity.this);

        recyclerView.setAdapter(adapter);
        adapter.setClickListener((v, position) -> {

            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            intent.putExtra("index", position);
            startActivity(intent);

        });

    }


}
