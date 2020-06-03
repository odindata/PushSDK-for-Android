package com.odin.push.demo.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.odin.push.demo.BasePushActivity;
import com.odin.push.demo.R;
import com.odin.push.demo.utils.HttpUtil;
import com.odin.push.demo.utils.PushRequest;

public class TimerNotificationActivity extends BasePushActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "TimerNotification";
    private long taskTimer = MIN;
    private static final long MIN = 60 * 1000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_timer_notification;
    }

    @Override
    public void initViews() {
        super.initViews();
        RadioGroup radioGroup = findViewById(R.id.radioGroup_timer);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton4);
        RadioButton radioButton5 = findViewById(R.id.radioButton5);
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
        PushRequest.pushUpdateApi(1, content, 1, taskTimer, null, new HttpUtil.CallBack() {
            @Override
            public void onSucceed(String body) {
                Log.i(TAG, "onSucceed: " + body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TimerNotificationActivity.this, "发送推送成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(int code) {
                Log.i(TAG, "onFailure: " + code);
            }

            @Override
            public void onConnectFailure(Throwable throwable) {
                Log.i(TAG, "onConnectFailure: " + throwable.getMessage());
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.str_timer_notification_test);
    }

    @Override
    protected String getPushTitle() {
        return getString(R.string.str_timer_notification_test_content);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
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
