package com.hjianfei.ticket.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by HJianFei on 2016-7-18.
 */
public class MyUser extends BmobUser {
    private String real_name;
    private String user_id;
    private String user_id_type;

    public String getUser_id_type() {
        return user_id_type;
    }

    public void setUser_id_type(String user_id_type) {
        this.user_id_type = user_id_type;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
