package com.odin.push.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.odin.push.demo.ui.main.AppNotificationActivity;
import com.odin.push.demo.ui.main.InAppPushActivity;
import com.odin.push.demo.ui.main.LocalNotificationActivity;
import com.odin.push.demo.ui.main.PushOpenPageActivity;
import com.odin.push.demo.ui.main.PushOpenUrlActivity;
import com.odin.push.demo.ui.main.TimerNotificationActivity;
import com.odin.push.demo.ui.open.OpenPageDetailActivity;
import com.odin.push.demo.ui.open.OpenUrlActivity;
import com.odin.pushcore.OdinPush;
import com.odin.pushcore.OdinPushReceiver;
import com.odin.pushcore.impl.notify.OdinPushCustomMessage;
import com.odin.pushcore.impl.notify.OdinPushNotifyMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private OdinPushReceiver odinPushReceiver;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        registerOdinPushReceiver();
        dealPushResponse(getIntent());
    }

    /**
     * 防止程序在后台，获取不到数据
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        dealPushResponse(intent);
    }

    public void startInAppPush(View view) {
        startToActivity(InAppPushActivity.class);
    }

    public void startAppNotification(View view) {
        startToActivity(AppNotificationActivity.class);
    }

    public void startTimerNotification(View view) {
        startToActivity(TimerNotificationActivity.class);
    }

    public void startLocalNotification(View view) {
        startToActivity(LocalNotificationActivity.class);
    }

    public void startPushAndOpenUrl(View view) {
        startToActivity(PushOpenUrlActivity.class);
    }

    public void startPushAndOpenPage(View view) {
        startToActivity(PushOpenPageActivity.class);
    }

    /**
     * 点击通知跳转到APP内部的数据处理以及后续的跳转逻辑
     *
     * @param intent intent
     */
    private void dealPushResponse(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (key.equals("msg")) {
                //通过OdinPush通道、华为、小米、魅族、（应用在前台时接收的推送），
                //点击通知则启动应用附加数据再次进行判断处理
                //非推送link跳转，而是通过默认进入app入口页，通过msg获取传过来的数据
                OdinPushNotifyMessage notifyMessage = (OdinPushNotifyMessage) bundle.getSerializable(key);
                if (notifyMessage == null) {
                    return;
                }
                HashMap<String, String> params = notifyMessage.getExtrasMap();
                if (params != null && params.containsKey(Constant.ODIN_PUSH_DEMO_URL)) {
                    openUrl(params);
                } else if (params != null && params.containsKey(Constant.ODIN_PUSH_DEMO_INTENT)) {
                    openPage(params);
                } else if (params != null && params.containsKey(Constant.ODIN_PUSH_DEMO_LINK)) {
                    openAct(params);
                }
            } else if (key.equals("data")) {
                String data = (String) bundle.get("data");
                Log.d(TAG, "odinpush_link_v: " + data);
                //通过推送link跳转获得数据可通过data取出传过来的数据，而不是用msg
                Toast.makeText(this, "olink_value" + data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openUrl(HashMap<String, String> params) {
        String url = params.get(Constant.ODIN_PUSH_DEMO_URL);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Log.d(TAG, "openUrl: " + url);
        System.out.println("url:" + url);
        OpenUrlActivity.newInstance(this, url);
    }

    private void openPage(HashMap<String, String> params) {
        Intent intent = new Intent(this, OpenPageDetailActivity.class);
        intent.putExtra("key1", "value1");
        intent.putExtra("key2", "value2");
        intent.putExtra("key3", "value3");
        String uri = params.get(Constant.ODIN_PUSH_DEMO_INTENT);
        Log.d("odinPush", "openPage: " + uri);
        if (!TextUtils.isEmpty(uri)) {
            try {
                startActivity(Intent.parseUri(uri, 0));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void openAct(HashMap<String, String> params) {
        String uri = params.get(Constant.ODIN_PUSH_DEMO_LINK);
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        Intent intent = new Intent(null, Uri.parse(uri));
        startActivity(intent);
    }

    /**
     * 添加推送监听，可监听接收到的自定义消息（透传消息）、通知消息、通知栏点击事件、别名和标签变更操作
     */
    private void registerOdinPushReceiver() {
        odinPushReceiver = new OdinPushReceiver() {
            @Override
            public void onCustomMessageReceive(Context context, OdinPushCustomMessage message) {
                //接收到自定义消息（透传消息）
                Log.i(TAG, "自定义消息（透传消息）: " + message.toString());
            }

            @Override
            public void onNotifyMessageReceive(Context context, OdinPushNotifyMessage message) {
                //接收到通知消息
                Log.i(TAG, "通知消息: " + message.toString());
            }

            @Override
            public void onNotifyMessageOpenedReceive(Context context, OdinPushNotifyMessage message) {
                //通知被点击事件
                Log.i(TAG, "通知被点击事件: " + message.toString());
            }

            @Override
            public void onTagsCallback(Context context, String[] tags, int operation, int errorCode) {
                //标签操作回调
                Log.i(TAG, "标签操作回调, tags: " + Arrays.toString(tags) + ", operation: " + operation + ", errorCode: " + errorCode);
            }

            @Override
            public void onAliasCallback(Context context, String alias, int operation, int errorCode) {
                //别名操作回调
                Log.i(TAG, "别名操作回调, alias: " + alias + ", operation: " + operation + ", errorCode: " + errorCode);
            }
        };
        OdinPush.addPushReceiver(odinPushReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除监听
        OdinPush.removePushReceiver(odinPushReceiver);
    }
}
