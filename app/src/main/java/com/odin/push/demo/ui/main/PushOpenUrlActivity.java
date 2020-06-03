package com.odin.push.demo.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.odin.push.demo.BasePushActivity;
import com.odin.push.demo.Constant;
import com.odin.push.demo.R;
import com.odin.push.demo.utils.HttpUtil;
import com.odin.push.demo.utils.PushRequest;

public class PushOpenUrlActivity extends BasePushActivity {

    private EditText mEtUrl;
    private static final String TAG = "PushOpenUrlActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_push_open_url;
    }

    @Override
    public void initViews() {
        super.initViews();
        mEtUrl = findViewById(R.id.et_push_url);
        mEtUrl.setVisibility(View.VISIBLE);
    }

    @Override
    protected void startPush() {
        String content = mEtPushContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "推送内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = mEtUrl.getText().toString().trim();
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, "链接地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PushRequest.pushUpdateApi(1, content, 0, 0, "{" + Constant.ODIN_PUSH_DEMO_URL + ":\"" + url + "\"}", new HttpUtil.CallBack() {
            @Override
            public void onSucceed(String body) {
                Log.i(TAG, "onSucceed: " + body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PushOpenUrlActivity.this, "发送推送成功", Toast.LENGTH_SHORT).show();
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
        return getString(R.string.str_push_open_link);
    }

    @Override
    protected String getPushTitle() {
        return getString(R.string.str_open_link_test_content);
    }
}
