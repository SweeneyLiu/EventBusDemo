package com.lsw.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // 主线程调用
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusMain(String str){
        Log.i("TAG", "MAIN:"+str+" Thread="+Thread.currentThread().getId());
    }

    // 1.发布线程为主线程，新开线程调用
    // 2.发布线程为子线程，发布线程调用
    // 不能并发处理
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void eventBusBg(String str){
        Log.i("TAG", "BACKGROUND:"+str+" Thread="+Thread.currentThread().getId());
    }

    // 在发布线程调用，默认值
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void eventBusPosting(String str){
        Log.i("TAG", "POSTING:"+str+" Thread="+Thread.currentThread().getId());
    }

    // 每次都新开线程调用（可以并发处理）
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void eventBusAsync(String str){
        Log.i("TAG", "ASYNC:"+str+" Thread="+Thread.currentThread().getId());
    }

}
