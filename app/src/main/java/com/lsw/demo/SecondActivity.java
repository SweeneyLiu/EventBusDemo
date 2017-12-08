package com.lsw.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    private Button btn_send;
    private Button btn_send_sticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send_sticky = (Button)findViewById(R.id.btn_send_sticky);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送消息
                EventBus.getDefault().post(new FirstEvent("eventbus3.0测试"));

//                EventBus.getDefault().post(new FirstEvent("eventbus3.0测试优先级"));
            }
        });

        btn_send_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new FirstEvent("eventbus3.0粘性事件测试"));
                finish();
            }
        });

    }
}
