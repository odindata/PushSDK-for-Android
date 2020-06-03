package com.odin.push.demo.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private HttpURLConnection connection;
    private Charset charset = Charset.forName("UTF-8");
    private int readTimeout = 32000;
    private int connectTimeout = 10000;
    private String method = "POST";
    private boolean doInput = true;
    private Map<String, String> headers = new HashMap<>();
    private String data = null;
    private byte[] AESKey = null;
    private static final String DEBUG_BASE_URL = " http://www.odinanalysis.com/";

    public static HttpUtil connect(String url) throws IOException {
        url = DEBUG_BASE_URL + url;
        return new HttpUtil((HttpURLConnection) new URL(url).openConnection());
    }

    /**
     * 禁止new实例
     */
    private HttpUtil() {
    }

    private HttpUtil(HttpURLConnection connection) {
        this.connection = connection;
    }

    /**
     * 添加Headers
     *
     * @param map
     */
    public HttpUtil setHeaders(Map<String, String> map) {
        headers.putAll(map);
        return this;
    }

    /**
     * 写入数据,接受Map<String,String>或String类型<br>
     * 例如POST时的参数<br>
     * demo=1&name=2
     */
    public HttpUtil setData(Object object) {
        if (object == null) {
            return this;
        } else if (object instanceof String) {
            this.data = (String) object;
        } else if (object instanceof Map) {
            Map map = (Map) object;
            StringBuilder builder = new StringBuilder();
            for (Object key : map.keySet()) {
                builder.append(key + "=" + map.get(key) + "&");
            }
            this.data = builder.toString().substring(0, builder.length() > 0 ? builder.length() - 1 : builder.length());
        }
        return this;
    }

    /**
     * 发起请求
     */
    public void execute(final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //添加请求头
                if (headers != null) {
                    for (String key : headers.keySet()) {
                        connection.setRequestProperty(key, headers.get(key));
                    }
                }
                try {
                    //设置读去超时时间为10秒
                    connection.setReadTimeout(readTimeout);
                    //设置链接超时为10秒
                    connection.setConnectTimeout(connectTimeout);
                    //设置请求方式,GET,POST
                    connection.setRequestMethod(method.toUpperCase());
                    //接受输入流
                    connection.setDoInput(doInput);
                    connection.setDoOutput(true);
                    int length = data.getBytes().length;
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Content-Length", length + "");
                    connection.connect();
                    //写入参数
                    if (data != null) {
                        //添加请求参数，注意：如果是GET请求，参数要写在URL中
                        OutputStream output = connection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, charset));
                        //写入参数 用&分割。
                        writer.write(data);
                        writer.flush();
                        writer.close();
                    }
                    Log.d("odinPush", "httpUtil--ResponseCode" + connection.getResponseCode());
                    if (connection.getResponseCode() == 200) {
                        InputStream inputStream = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(inputStream, "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        String body = br.readLine();
                        Log.d("odinPush", "信息返回 ：" + body);
                        callBack.onSucceed(body);
                        inputStream.close();
                    } else {
                        callBack.onFailure(connection.getResponseCode());
                    }

                    connection.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    callBack.onConnectFailure(e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface CallBack {
        void onSucceed(String body);

        void onFailure(int code);

        void onConnectFailure(Throwable throwable);
    }

}