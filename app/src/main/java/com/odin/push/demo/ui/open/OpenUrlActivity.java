package com.odin.push.demo.ui.open;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.odin.push.demo.BaseActivity;
import com.odin.push.demo.R;

public class OpenUrlActivity extends BaseActivity {

    private String url;
    private static final String EXTRA_URL = "url";

    private WebView webView;

    public static void newInstance(Context context, String url) {
        Intent intent = new Intent(context, OpenUrlActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_url;
    }

    @Override
    public void initViews() {
        webView = findViewById(R.id.webview);

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(EXTRA_URL);
        }
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }
}
