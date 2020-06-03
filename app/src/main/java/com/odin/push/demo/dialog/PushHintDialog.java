package com.odin.push.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.odin.push.demo.R;

public class PushHintDialog extends Dialog {

    private String content;
    private TextView mTvContent;

    public PushHintDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_push_hint);

        mTvContent = findViewById(R.id.tv_dialog_push_hint_content);
        mTvContent.setText(content == null ? "" : content);
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
