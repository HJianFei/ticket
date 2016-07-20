package com.hjianfei.ticket.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.hjianfei.ticket.bean.Trains;

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
 *
 * @description 车次票价查询
 */
public class TrainMoneyNetUtil {
    private Handler mHandler;
    private Trains.ResultBean resultBean;
    private int type;

    public TrainMoneyNetUtil(Handler mHandler, Trains.ResultBean resultBean, int type) {
        this.mHandler = mHandler;
        this.resultBean = resultBean;
        this.type = type;
    }

    //配置您申请的KEY
    public static final String APPKEY = "e5fae156b3669fd164290939d60224d7";

    public void getTrainMoney() {
        String result = null;
        final String url = "http://apis.juhe.cn/train/ticket.price.php";//请求接口地址
        final Map params = new HashMap();//请求参数
        params.put("train_no", resultBean.getTrain_no());//列次编号，对应12306订票①：查询车次中返回的train_no
        params.put("from_station_no", resultBean.getFrom_station_name());//出发站序号，对应12306订票①：查询车次中返回的from_station_no
        params.put("to_station_no", resultBean.getTo_station_name());//出发站序号，对应12306订票①：查询车次中返回的to_station_no
        params.put("date", "");//默认当天，格式：2014-12-25
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        new Thread(new Runnable() {
            @Override
            public void run() {
                String trainsJson = getMoney(url, params, "GET");
                if (trainsJson != null) {
                    System.out.println(trainsJson);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultBean", resultBean);
                    bundle.putString("trainsJson", trainsJson);
                    Message message = Message.obtain();
                    message.obj = bundle;
                    message.what = type;
                    mHandler.sendMessage(message);
                }

            }
        }).start();

    }

    private String getMoney(String strUrl, Map params, String method) {
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
            message.what = 1;
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
