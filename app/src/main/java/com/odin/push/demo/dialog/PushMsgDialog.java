package com.odin.push.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.odin.push.demo.R;

public class PushMsgDialog extends Dialog {

    private String content;
    private TextView mTvContent;

    public PushMsgDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_push_msg);

        mTvContent = findViewById(R.id.tv_dialog_push_content);
        mTvContent.setText(content == null ? "" : content);
        Button mBtnEnsure = findViewById(R.id.btn_ensure);
        mBtnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public void setContent(String content) {
        this.content = content;
        if (mTvContent != null) {
            mTvContent.setText(content == null ? "" : content);
        }
    }
}
