package com.odin.push.demo;

public class Constant {

    //推送跳转指定界面获取指定界面uri的固定key字段名:
    public static final String ODIN_PUSH_DEMO_URL = "linkUrl";//打开链接
    //    public final static String ODIN_PUSH_DEMO_URL = "url";//打开链接
    public static final String ODIN_PUSH_DEMO_INTENT = "intent";
    public static final String ODIN_PUSH_DEMO_LINK = "scheme";//打开页面
    //    public final static String ODIN_PUSH_DEMO_LINK = "odinpush_link_k";//打开页面

    /**
     * 这里的配置需要和AndroidManifest.xml中跳转页面的配置保持一致
     */
    public static final String SCHEME_PAGE_DETAIL = "odin://com.odin.push.demo/detail";
    public static final String SCHEME_PAGE_NOTIFICATION = "odin://com.odin.push.demo/notification";
}
