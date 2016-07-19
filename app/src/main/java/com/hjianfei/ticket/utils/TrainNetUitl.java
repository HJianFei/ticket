package com.hjianfei.ticket.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HJianFei on 2016-7-17.
 */
public class TrainNetUitl {

    private String start;
    private String end;
    private String date;
    private Handler mHandler;
    private int type;

    public TrainNetUitl(String start_state, String end_state, String date, Handler mHandler, int type) {
        this.start = start_state;
        this.date = date;
        this.end = end_state;
        this.mHandler = mHandler;
        this.type = type;
    }

    public void getDatas() {
        //配置您申请的KEY
        final String APPKEY = "e5fae156b3669fd164290939d60224d7";
        final String url = "http://apis.juhe.cn/train/yp";//请求接口地址
        final Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype", "json");//返回数据的格式,xml或json，默认json
        params.put("from", start);//出发站,如：上海虹桥
        params.put("to", end);// 到达站,如：温州南
        params.put("date", "");//出发日期，默认今日
        params.put("tt", "");//车次类型，默认全部，如：G(高铁)、D(动车)、T(特快)、Z(直达)、K(快速)、Q(其他)
        new Thread(new Runnable() {
            @Override
            public void run() {
                String trainsJson = getTrains(url, params, "GET");
                if (trainsJson != null) {
                    Message message = Message.obtain();
                    message.obj = trainsJson;
                    message.what = type;
                    System.out.println(trainsJson);
                    mHandler.sendMessage(message);
                }

            }
        }).start();
    }


    private String getTrains(String strUrl, Map params, String method) {
        final String DEF_CHATSET = "UTF-8";
        final int DEF_CONN_TIMEOUT = 10000;
        final int DEF_READ_TIMEOUT = 10000;
        String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("MyUser-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            if (params != null && method.equals("POST")) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(urlencode(params));
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            SystemClock.sleep(2000);
            Message message = Message.obtain();
            message.what = 2;
            mHandler.sendMessage(message);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;

    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
