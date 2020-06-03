package com.odin.push.demo.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.odin.push.demo.BasePushActivity;
import com.odin.push.demo.R;
import com.odin.push.demo.utils.HttpUtil;
import com.odin.push.demo.utils.PushRequest;

public class AppNotificationActivity extends BasePushActivity {

    private static final String TAG = "AppNotificationActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_in_app_notification;
    }

    @Override
    protected void startPush() {
        String content = mEtPushContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "推送内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PushRequest.pushUpdateApi(1, content, 0, 0, null, new HttpUtil.CallBack() {
            @Override
            public void onSucceed(String body) {
                Log.i(TAG, "onSucceed: " + body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AppNotificationActivity.this, "发送推送成功", Toast.LENGTH_SHORT).show();
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
        return getString(R.string.str_app_notification_test);
    }

    @Override
    protected String getPushTitle() {
        return getString(R.string.str_app_notification_test_content);
    }
}
