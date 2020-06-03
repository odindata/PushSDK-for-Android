package com.odin.push.demo.ui.main;

import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.odin.push.demo.BasePushActivity;
import com.odin.push.demo.R;
import com.odin.pushcore.OdinPush;
import com.odin.pushcore.impl.notify.OdinPushLocalNotification;

import java.util.HashMap;
import java.util.Random;

public class LocalNotificationActivity extends BasePushActivity implements RadioGroup.OnCheckedChangeListener {

    private long taskTimer = MIN;
    private static final long MIN = 60 * 1000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_notification;
    }

    @Override
    public void initViews() {
        super.initViews();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton radioButton0 = findViewById(R.id.radioButton0);
        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton4);
        RadioButton radioButton5 = findViewById(R.id.radioButton5);
        setRadioButtonDrawable(radioButton0);
        setRadioButtonDrawable(radioButton1);
        setRadioButtonDrawable(radioButton2);
        setRadioButtonDrawable(radioButton3);
        setRadioButtonDrawable(radioButton4);
        setRadioButtonDrawable(radioButton5);
    }

    @Override
    protected void startPush() {
        String content = mEtPushContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "推送内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        showLocalNotification(content, taskTimer, null);
    }

    public void showLocalNotification(String content, long timestamp, HashMap<String, String> extrasMap) {
        OdinPushLocalNotification odinPushLocalNotification = new OdinPushLocalNotification();
        odinPushLocalNotification.setTitle("OdinPushDemo本地通知测试");
        odinPushLocalNotification.setContent(content);
        //可设置不进行声音提醒，默认声音、振动、指示灯
        odinPushLocalNotification.setShake(true);
        odinPushLocalNotification.setLight(true);
        odinPushLocalNotification.setVoice(true);
        odinPushLocalNotification.setTimestamp(timestamp + System.currentTimeMillis());
        odinPushLocalNotification.setExtrasMap(extrasMap);
        odinPushLocalNotification.setNotificationId(new Random().nextInt());
        OdinPush.addLocalNotification(odinPushLocalNotification);

        Toast.makeText(LocalNotificationActivity.this, "发送推送成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.str_local_notification_test);
    }

    @Override
    protected String getPushTitle() {
        return getString(R.string.str_local_notification_test_content);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton0:
                taskTimer = 0;
                break;
            case R.id.radioButton1:
                taskTimer = MIN;
                break;
            case R.id.radioButton2:
                taskTimer = 2 * MIN;
                break;
            case R.id.radioButton3:
                taskTimer = 3 * MIN;
                break;
            case R.id.radioButton4:
                taskTimer = 4 * MIN;
                break;
            case R.id.radioButton5:
                taskTimer = 5 * MIN;
                break;
        }
    }
}