package com.lsw.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EventBus.getDefault().post("from second activity mainThread: info");

        Log.i("TAG", "Post thread="+Thread.currentThread().getId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post("from second activity childThread: info");
                Log.i("TAG", "Post thread="+Thread.currentThread().getId());
            }
        }).start();
    }
}
