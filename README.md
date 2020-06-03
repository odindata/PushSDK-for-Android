# 奥丁PushSDK

- [奥丁PushSDK官网](http://www.odinanalysis.com/push.html)

## 一、集成说明

### 配置build.gradle

- 项目根目录的build.gradle

```groovy
buildscript {
    repositories {
        ...
        maven {url "http://maven.odinlk.com:58080/repository/android/" }
    }
    dependencies {
       ...
    }
}
allprojects {
    repositories {
       ...
        maven {
            url "http://maven.odinlk.com:58080/repository/android/"
        }
    }
}
```

- module工程的build.gradle

```groovy
//核心包和工具包
implementation 'com.odin.common:odincommon:1.0.1_beta'
implementation 'com.odin.common:odintools:1.0.1_beta'
implementation 'com.odin:OdinPush:1.0.2_beta'

//渠道包，按需集成
//集成华为渠道
implementation'com.odin.push.plugins:huawei:1.0.1_beta'
//集成魅族渠道
implementation 'com.odin.push.plugins:meizu:1.0.1_beta'
//集成小米渠道
implementation 'com.odin.push.plugins:xiaomi:1.0.1_beta'
```

### AndroidManifest配置

#### 权限授权

- 请在AndroidManifest中添加如下权限，NOTE: 请将${PACKAGE_NAME}换成实际应用的包名

```xml
<!--公共权限-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission
    android:name="android.permission.GET_ACCOUNTS"
    android:maxSdkVersion="22" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.VIBRATE"/>
```

```xml
<!--华为权限-->
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
```

```xml
<!--小米权限-->
<uses-permission android:name="${PACKAGE_NAME}.permission.MIPUSH_RECEIVE" />
<permission
    android:name="${PACKAGE_NAME}.permission.MIPUSH_RECEIVE"
    android:protectionLevel="signature" />
```

```xml
<!--魅族权限  -->
<uses-permission android:name="com.meizu.flyme.push.permission.RECEIVE" />
<uses-permission android:name="${PACKAGE_NAME}.push.permission.MESSAGE" />
<uses-permission android:name="com.meizu.c2dm.permission.RECEIVE" />
<uses-permission android:name="${PACKAGE_NAME}.permission.C2D_MESSAGE" />
<permission
    android:name="${PACKAGE_NAME}.permission.C2D_MESSAGE"
    android:protectionLevel="signature" />
<permission
    android:name="com.odin.odinpush.push.permission.MESSAGE"
    android:protectionLevel="signature" />
```

#### **OdinPush服务组件**

```xml
<service
    android:name="com.odin.pushcore.OdinPushService"
    android:exported="true"
    android:process=":odinpush" />

<service
    android:name="com.odin.pushcore.impl.PushJobService"
    android:permission="android.permission.BIND_JOB_SERVICE"></service>

<receiver android:name="com.odin.pushcore.impl.OdinPushReceiver">
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
    </intent-filter>
</receiver>

<meta-data
android:name="Odin-AppKey"
android:value="odinpush应用的AppKey" />
<meta-data
android:name="Odin-AppSecret"
android:value="odinpush应用的AppSecret" />
```

#### **配置 meta-data 注意项**

meta-data value值如果为纯数字 大小不能大于9935297，如果大于请在在首位加上“\”
例如：android:name="com.odin.push.xiaomi.appid"
		    android:value="\2882303761517992114" />

但是如果含有特殊符号或者字母不用添加。

#### **小米配置渠道配置**

```xml
<!-- ************************小米*********************** -->
<service
    android:name="com.xiaomi.mipush.sdk.MessageHandleService"
    android:enabled="true" />
<service
    android:name="com.xiaomi.mipush.sdk.PushMessageHandler"
    android:enabled="true"
    android:exported="true" />

<receiver
    android:name="com.xiaomi.push.service.receivers.NetworkStatusReceiver"
    android:exported="true">
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />

        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</receiver>
<receiver
    android:name="com.odin.pushcore.plugins.xiaomi.PushXiaoMiRevicer"
    android:exported="true">
    <intent-filter>
        <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
    </intent-filter>
    <intent-filter>
        <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
    </intent-filter>
    <intent-filter>
        <action android:name="com.xiaomi.mipush.ERROR" />
    </intent-filter>
</receiver>

<meta-data
    android:name="com.odin.push.xiaomi.appid"
    android:value="\${小米开放平台申请的push服务的APP的id}" />
<meta-data
    android:name="com.odin.push.xiaomi.appkey"
    android:value="\${小米开放平台申请的push服务的APP的key}" />
```

#### **华为渠道配置**

```xml
<meta-data
    android:name="com.huawei.hms.client.appid"
    android:value="\${华为开放平台申请的push服务的APP的id}" />

<receiver android:name="com.odin.pushcore.plugins.huawei.PushHaiWeiRevicer">
    <intent-filter>
        <action android:name="com.huawei.android.push.intent.REGISTRATION" />
        <action android:name="com.huawei.android.push.intent.RECEIVE" />
        <action android:name="com.huawei.android.push.intent.CLICK" />
        <action android:name="com.huawei.intent.action.PUSH_STATE" />
    </intent-filter>
</receiver>

<provider 
    android:authorities="${PACKAGE_NAME}.hms.update.provider"
    android:exported="false" android:grantUriPermissions="true" 		           android:name="com.huawei.hms.update.provider.UpdateProvider"/>
```

#### **魅族渠道配置**

```xml
<receiver android:name="com.odin.pushcore.plugins.meizu.PushMeiZuRevicer">
    <intent-filter>
        <action android:name="com.meizu.flyme.push.intent.MESSAGE" />
        <action android:name="com.meizu.flyme.push.intent.REGISTER.FEEDBACK" />
        <action android:name="com.meizu.flyme.push.intent.UNREGISTER.FEEDBACK" />
        <action android:name="com.meizu.c2dm.intent.REGISTRATION" />
        <action android:name="com.meizu.c2dm.intent.RECEIVE" />
        <category android:name="${PACKAGE_NAME}" />
    </intent-filter>
</receiver>

<meta-data
    android:name="com.odin.push.meizu.appid"
    android:value="${魅族开放平台申请的push服务的APP的id}" />
<meta-data
    android:name="com.odin.push.meizu.appkey"
    android:value="${魅族开放平台申请的push服务的APP的appkey}" />
```



### 混淆设置

OdinAnalysisSDK已经做了混淆处理，再次混淆会导致不可预期的错误，请在您的混淆脚本中添加如下的配置，跳过对OdinAnalysisSDK的混淆操作

```
-keep class com.odin.**{*;}
-dontwarn com.odin.**
```

如支持厂商推送，混淆脚本也许添加跳过对厂商的混淆操作：

```
-keep **class** com.huawei.**{*;}
-keep **class** com.meizu.**{*;}
-keep **class** com.xiaomi.**{*;}
-dontwarn com.huawei.**
-dontwarn com.meizu.**
-dontwarn com.xiaomi.**
```



## 二、**接口功能介绍**

| **接口**                                 | **参数**                                             | **说明**                                                     |
| ---------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
| addPushReceiver                          | OdinPushReceiver                                     | 推送监听接口                                                 |
| removePushReceiver                       | OdinPushReceiver                                     | 移除推送监听接口                                             |
| getRegistrationId                        | OdinPushCallback<String>                             | 获取注册id，在参数OdinPushCallback的onCallback（String）中回调注册ID |
| setAlias                                 | String 字符串                                        | 设置别名                                                     |
| deleteAlias                              |                                                      | 删除别名                                                     |
| getAlias                                 |                                                      | 获取别名，获取的结果会在推送监听OdinPushReceiver的onAliasCallback()回调返回 |
| addTags                                  | String[]                                             | 添加标签                                                     |
| deleteTags                               | String[]                                             | 删除标签                                                     |
| clean Tags.                              |                                                      | 清空标签                                                     |
| getTags                                  |                                                      | 获取标签，获取的结果会在推送监听OdinPushReceiver的onTagsCallback()回调返回 |
| setSilenceTime                           | Int startHourInt startMinuteInt endHourInt endMinute | 设置通知静音时段                                             |
| addLoaclNotfication                      | OdinPushLocalNotification                            | 添加本地推送通知                                             |
| RemoveLocalNotification                  | Int notificationId                                   | 移除本地通知                                                 |
| isPushStopped                            |                                                      | 判断推送服务是否以及停止                                     |
| restartPush                              |                                                      | 重新开始推送服务                                             |
| stopPush                                 |                                                      | 停止推送，停止后键不会接收到推送，仅可通过restartPush重新开发 |
| setCilckNotificationTolaunchMainActivity | Boolean enable                                       | 设置点击通知后是否开启主页                                   |
| setNotifyIcon                            | Int resId                                            | 设置通知图标，默认使用应用图标                               |
| setAppForegroundHiddenNotification       | Boolean hidden                                       | 设置应用运行在前台不显示通知                                 |

### 初始化

- 初始化方法必须在宿主应用application.onCreate函数中调用基础组件包提供的初始化函数：

```Java
 OdinSDK.init(this);
```

### 监听

```java
OdinPush.addPushReceiver(new OdinPushReceiver() {

	@Override
	public void onCustomMessageReceive(Context context, OdinPushCustomMessage message) {
		//接收自定义消息
	}

	@Override
	public void onNotifyMessageReceive(Context context, OdinPushNotifyMessage message) {
		//接收通知消息
	}

	@Override
	public void onNotifyMessageOpenedReceive(Context context, OdinPushNotifyMessage message) {
		//接收通知消息被点击事件
	}
    
	@Override
	public void onTagsCallback(Context context, String[] tags, int operation, int errorCode) {
		//接收tags的增改删查操作
	}

	@Override
	public void onAliasCallback(Context context, String alias, int operation, int errorCode) {
		//接收alias的增改删查操作
	}
});
```

**在退出已经设置了监听的界面时，调用移除监听接口：**

```java
OdinPush.removePushReceiver(receiver);
```

### **别名**

- **设置别名**

描述：别名是唯一的，多次调用，以最后一次设置为准，会进行覆盖；与注册Id是一一对应的。是否设置成功，会在addPushReceiver()->OdinPushReceiver-> onAliasCallback(Context context, String alias, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取别名操作；当operation为1时，表示设置别名操作；当operation为2时，表示删除别名操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.setAlias(“test1”);
```

- **删除别名**

描述：是否删除成功，会在addPushReceiver()->OdinPushReceiver-> onAliasCallback(Context context, String alias, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取别名操作；当operation为1时，表示设置别名操作；当operation为2时，表示删除别名操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.setAlias("deleteAlias")
```

- **获取别名**

描述：是否获取成功，会在addPushReceiver()->OdinPushReceiver-> onAliasCallback(Context context, String alias, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取别名操作；当operation为1时，表示设置别名操作；当operation为2时，表示删除别名操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.getAlias();
```

### **标签**

- **添加标签**

描述：标签可以添加多个，每次调用都会在原来的基础上进行追加。

是否获取成功，会在addPushReceiver()->OdinPushReceiver-> onTagsCallback(Context context, String[] tags, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取标签操作；当operation为1时，表示设置标签操作；当operation为2时，表示删除标签操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.addTags(new String[]{addTags});
```

- **删除标签**

描述：是否获取成功，会在addPushReceiver()->OdinPushReceiver-> onTagsCallback(Context context, String[] tags, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取标签操作；当operation为1时，表示设置标签操作；当operation为2时，表示删除标签操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.deleteTags(new String[]{delTags});
```

- **获取标签**

描述：是否获取成功，会在addPushReceiver()->OdinPushReceiver-> onTagsCallback(Context context, String[] tags, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取标签操作；当operation为1时，表示设置标签操作；当operation为2时，表示删除标签操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.getTags();
```

- **清空标签**

描述：是否获取成功，会在addPushReceiver()->OdinPushReceiver-> onTagsCallback(Context context, String[] tags, int operation, int errorCode)中进行回调返回。当operation为0时，表示获取标签操作；当operation为1时，表示设置标签操作；当operation为2时，表示删除标签操作。当errorCode为0时，表示操作成功；当errorCode非0时，表示操作失败。

```java
OdinPush.cleanTags();
```

### 注册id

描述：获取注册id，同一台设备，同个appkey对应一个注册id。

```java
OdinPush.getRegistrationId(new OdinPushCallback<String>() {
	@Override
	public void onCallback(String rid){
		System.out.println("RegistrationId:" + rid);
    }
});
```

### 设置静音时间

描述：设置静音时间段，几点几分开始到几点几分结束，这段时间属于静音时间段，接收到推送时，提醒类型属于静音状态。

```java
OdinPush.setSilenceTime(20, 0, 0, 0);//设置静音时间段晚上20:00到00:00
```

### 本地通知

描述：添加本地通知。不通过服务器推送，客户端主动发送通知。

```java
OdinPushLocalNotification localNotification = new OdinPushLocalNotification();
localNotification.setTitle("本地通知标题");
localNotification.setContent("本地通知内容");
localNotification.setNotificationId(本地通知ID);
...
OdinPush.addLocalNotification(localNotification);
```

- 移除本地通知

```java
OdinPush.removeLocalNotification(想要移除的本地通知ID);
```

- 清除本地通知

```java
OdinPush.clearLocalNotifications();
```

### 推送服务

- 判断是否停止推送

```java
OdinPush.isPushStopped();
```

- 重新启动推送服

描述：推送服务停止后，重新启动推送服务，继续接收推送。

```java
OdinPush.restartPush();
```

- 停止推送服务

描述：停止推送服务，不继续接收推送。

```java
OdinPush.stopPush();
```

- 设置点击通知是否启动默认页

描述：设置点击通知是否启动默认页。默认为启动。

```java
OdinPush.setClickNotificationToLaunchMainActivity(true);
```

- 设置通知图标

描述：设置通知图标。通知默认使用应用图标，调用此方法来修改通知图标。

```java
OdinPush.setNotifyIcon(R.mipmap.ic_launcher);
```

- 设置应用在前台时接收到推送不显示通知

描述：设置应用在前台时接收到推送不显示通知。默认是应用在前台是接收到推送则显示通知。true表示应用在前台时接收到推送不显示通知；false表示应用在前台时接收到推送显示通知。

```java
OdinPush.setAppForegroundHiddenNotification(true);
```

