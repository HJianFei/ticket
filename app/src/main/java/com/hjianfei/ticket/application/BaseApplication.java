package com.hjianfei.ticket.application;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by HJianFei on 2016-7-18.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
    }

    private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("a3e2857bd169295d7b049984f42369c7")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }
}
