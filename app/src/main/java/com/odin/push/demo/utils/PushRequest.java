package com.odin.push.demo.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.odin.odincommon.OdinSDK;
import com.odin.pushcore.OdinPush;
import com.odin.pushcore.OdinPushCallback;

import java.io.IOException;

public class PushRequest {

    private static final String TAG = "PushRequest";

    /**
     * 通过Rid推送
     *
     * @param type           推送类型 1：通知，2：自定义消息
     * @param content        推送内容
     * @param timingSendType 消息类型：1、定时；0、不是定时
     * @param taskTime       定时的时间
     * @param callback       回调
     */
    public static void pushUpdateApi(final int type, final String content, final int timingSendType, final long taskTime, final String extras, final HttpUtil.CallBack callback) {
        OdinPush.getRegistrationId(new OdinPushCallback<String>() {
            @Override
            public void onCallback(final String registrationId) {
                Log.i(TAG, "registrationId: " + registrationId);
                PushData pushData = new PushData();
                pushData.appkey = OdinSDK.getAPPKEY();
                pushData.plats = new Integer[]{1};
                pushData.androidstyle = 0;
                pushData.androidTitle = "OdinPushDemo标题";
                pushData.type = type;//通知/自定义消息
                pushData.workno = System.currentTimeMillis() + "";
                pushData.source = "developerPlatform";
                pushData.target = 4;
                pushData.setTagsType(1);
                pushData.setContent(content);
                pushData.setTitle("OdinPush_title");
                pushData.setRegistrationIds(new String[]{registrationId});
                pushData.setExtras(extras);

                pushData.setOfflineTime(1);//默认设置离线有效时间一天
                pushData.setWorkType(timingSendType);
                if (timingSendType == 1) {
                    pushData.setTaskTime(taskTime);
                }
                String pushJson = new Gson().toJson(pushData);
                Log.d(TAG, "自定义消息发送: " + pushJson);
                try {
                    HttpUtil.connect("api/devapi/push/update").setData(pushJson).execute(callback);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
