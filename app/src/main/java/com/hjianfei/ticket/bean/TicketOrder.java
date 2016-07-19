package com.hjianfei.ticket.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by HJianFei on 2016-7-18.
 */
public class TicketOrder extends BmobObject implements Serializable {
    private String date;
    private String cart_type;
    private String ticket_money;
    private String train_no;
    private String from_station_name;
    private String to_station_name;
    private String start_time;
    private String arrive_time;
    private String lishi;
    private String gr_num;
    private String qt_num;
    private String rw_num;
    private String rz_num;
    private String tz_num;
    private String wz_num;
    private String yw_num;
    private String yz_num;
    private String ze_num;
    private String zy_num;
    private String swz_num;
    private String user_name;
    private String user_id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCart_type() {
        return cart_type;
    }

    public void setCart_type(String cart_type) {
        this.cart_type = cart_type;
    }

    public String getTicket_money() {
        return ticket_money;
    }

    public void setTicket_money(String ticket_money) {
        this.ticket_money = ticket_money;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    public String getGr_num() {
        return gr_num;
    }

    public void setGr_num(String gr_num) {
        this.gr_num = gr_num;
    }

    public String getQt_num() {
        return qt_num;
    }

    public void setQt_num(String qt_num) {
        this.qt_num = qt_num;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getTz_num() {
        return tz_num;
    }

    public void setTz_num(String tz_num) {
        this.tz_num = tz_num;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getZe_num() {
        return ze_num;
    }

    public void setZe_num(String ze_num) {
        this.ze_num = ze_num;
    }

    public String getZy_num() {
        return zy_num;
    }

    public void setZy_num(String zy_num) {
        this.zy_num = zy_num;
    }

    public String getSwz_num() {
        return swz_num;
    }

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
    }

    @Override
    public String toString() {
        return "TicketOrder{" +
                "cart_type='" + cart_type + '\'' +
                ", ticket_money='" + ticket_money + '\'' +
                ", train_no='" + train_no + '\'' +
                ", from_station_name='" + from_station_name + '\'' +
                ", to_station_name='" + to_station_name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", arrive_time='" + arrive_time + '\'' +
                ", lishi='" + lishi + '\'' +
                ", gr_num='" + gr_num + '\'' +
                ", qt_num='" + qt_num + '\'' +
                ", rw_num='" + rw_num + '\'' +
                ", rz_num='" + rz_num + '\'' +
                ", tz_num='" + tz_num + '\'' +
                ", wz_num='" + wz_num + '\'' +
                ", yw_num='" + yw_num + '\'' +
                ", yz_num='" + yz_num + '\'' +
                ", ze_num='" + ze_num + '\'' +
                ", zy_num='" + zy_num + '\'' +
                ", swz_num='" + swz_num + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
