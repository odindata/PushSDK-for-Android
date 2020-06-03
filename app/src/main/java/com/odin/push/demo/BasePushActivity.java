package com.odin.push.demo;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public abstract class BasePushActivity extends BaseActivity {

    protected EditText mEtPushContent;

    @Override
    public void initViews() {
        initHeaderViews();
        initPushViews();
        initStartPushBtn();
    }

    private void initHeaderViews() {
        TextView mTvTitle = findViewById(R.id.tv_header_title);
        mTvTitle.setText(getHeaderTitle());

        findViewById(R.id.img_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPushViews() {
        TextView mTvTitle = findViewById(R.id.tv_push_title);
        mTvTitle.setText(getPushTitle());
        mEtPushContent = findViewById(R.id.et_push_content);
    }

    private void initStartPushBtn() {
        findViewById(R.id.btn_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPush();
            }
        });
    }

    public void setRadioButtonDrawable(RadioButton radioButton) {
        //定义底部标签图片大小和位置
        Drawable drawable = getResources().getDrawable(R.drawable.selector_timer);
        drawable.setBounds(0, 0, 62, 43);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, null, drawable, null);
    }

    protected abstract void startPush();

    protected abstract String getHeaderTitle();

    protected abstract String getPushTitle();

}
