package com.odin.push.demo.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.odin.push.demo.BasePushActivity;
import com.odin.push.demo.Constant;
import com.odin.push.demo.R;
import com.odin.push.demo.utils.HttpUtil;
import com.odin.push.demo.utils.PushRequest;

public class PushOpenPageActivity extends BasePushActivity implements RadioGroup.OnCheckedChangeListener {

    private String extras = "{" + Constant.ODIN_PUSH_DEMO_LINK + ": \"" + Constant.SCHEME_PAGE_DETAIL + "\"}";
    private static final String TAG = "PushOpenPageActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_push_open_page;
    }

    @Override
    public void initViews() {
        super.initViews();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        setRadioButtonDrawable(radioButton1);
        setRadioButtonDrawable(radioButton2);
    }

    @Override
    protected void startPush() {
        String content = mEtPushContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "推送内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PushRequest.pushUpdateApi(1, content, 0, 0, extras, new HttpUtil.CallBack() {
            @Override
            public void onSucceed(String body) {
                Log.i(TAG, "onSucceed: " + body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PushOpenPageActivity.this, "发送推送成功", Toast.LENGTH_SHORT).show();
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
        return getString(R.string.str_push_open_page);
    }

    @Override
    protected String getPushTitle() {
        return getString(R.string.str_open_page_test_content);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton1:
                extras = "{" + Constant.ODIN_PUSH_DEMO_LINK + ": \"" + Constant.SCHEME_PAGE_DETAIL + "\"}";
                break;
            case R.id.radioButton2:
                extras = "{" + Constant.ODIN_PUSH_DEMO_LINK + ": \"" + Constant.SCHEME_PAGE_NOTIFICATION + "\"}";
                break;
        }
    }
}
