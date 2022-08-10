package com.swi.remotecontrol.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.swi.baselibrary.BaseActivity;
import com.swi.remotecontrol.R;
import com.swi.remotecontrol.adpater.TestAdapter;
import com.swi.swiprotocollibrary.ComponentManager;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestActivity extends BaseActivity {

    private ListView lv_test_receive;

    private TestAdapter testAdapter;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        lv_test_receive = ((ListView) findViewById(R.id.lv_test_receive));
    }

    @Override
    public void initListener() {

    }

    int number = 0;

    @Override
    public void initData() {
        testAdapter = new TestAdapter(TestActivity.this);

        lv_test_receive.setAdapter(testAdapter);

//        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
//
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                number++;
//                Log.e("yuhao", "number: " + number);
//                runOnUiThread(() -> {
//                    testAdapter.setData(String.valueOf(number) + number + number + number);
//                });
//            }
//        }, 1, 1, TimeUnit.SECONDS);
//        ComponentManager.getInstance().getControlComponent().setControlCycleCallback();
    }
}