package com.hjianfei.ticket.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016-7-17.
 */
public class Trains implements Serializable {

    /**
     * reason : 查询成功
     * result : [{"train_no":"D2287","start_station_name":"上海虹桥","end_station_name":"深圳北","from_station_name":"上海虹桥","to_station_name":"温州南","start_time":"06:25","arrive_time":"10:53","train_class_name":"动车","day_difference":"0","lishi":"04:28","gr_num":"--","qt_num":"--","rw_num":"--","rz_num":"--","tz_num":"--","wz_num":"无","yw_num":"--","yz_num":"--","ze_num":"无","zy_num":"无","swz_num":"--"},{"train_no":"D3203","start_station_name":"上海虹桥","end_station_name":"厦门北","from_station_name":"上海虹桥","to_station_name":"温州南","start_time":"06:30","arrive_time":"11:09","train_class_name":"动车","day_difference":"0","lishi":"04:39","gr_num":"--","qt_num":"--","rw_num":"--","rz_num":"--","tz_num":"--","wz_num":"无","yw_num":"--","yz_num":"--","ze_num":"无","zy_num":"无","swz_num":"--"},{"train_no":"G7501","start_station_name":"上海虹桥","end_station_name":"苍南","from_station_name":"上海虹桥","to_station_name":"温州南","start_time":"07:00","arrive_time":"11:00","train_class_name":"","day_difference":"0","lishi":"04:00","gr_num":"--","qt_num":"--","rw_num":"--","rz_num":"--","tz_num":"--","wz_num":"165","yw_num":"--","yz_num":"--","ze_num":"无","zy_num":"无","swz_num":"15"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * train_no : D2287
     * start_station_name : 上海虹桥
     * end_station_name : 深圳北
     * from_station_name : 上海虹桥
     * to_station_name : 温州南
     * start_time : 06:25
     * arrive_time : 10:53
     * train_class_name : 动车
     * day_difference : 0
     * lishi : 04:28
     * gr_num : --
     * qt_num : --
     * rw_num : --
     * rz_num : --
     * tz_num : --
     * wz_num : 无
     * yw_num : --
     * yz_num : --
     * ze_num : 无
     * zy_num : 无
     * swz_num : --
     */

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        private String train_no;
        private String start_station_name;
        private String end_station_name;
        private String from_station_name;
        private String to_station_name;
        private String start_time;
        private String arrive_time;
        private String train_class_name;
        private String day_difference;
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

        public String getTrain_no() {
            return train_no;
        }

        public void setTrain_no(String train_no) {
            this.train_no = train_no;
        }

        public String getStart_station_name() {
            return start_station_name;
        }

        public void setStart_station_name(String start_station_name) {
            this.start_station_name = start_station_name;
        }

        public String getEnd_station_name() {
            return end_station_name;
        }

        public void setEnd_station_name(String end_station_name) {
            this.end_station_name = end_station_name;
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

        public String getTrain_class_name() {
            return train_class_name;
        }

        public void setTrain_class_name(String train_class_name) {
            this.train_class_name = train_class_name;
        }

        public String getDay_difference() {
            return day_difference;
        }

        public void setDay_difference(String day_difference) {
            this.day_difference = day_difference;
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
            return "ResultBean{" +
                    "train_no='" + train_no + '\'' +
                    ", start_station_name='" + start_station_name + '\'' +
                    ", end_station_name='" + end_station_name + '\'' +
                    ", from_station_name='" + from_station_name + '\'' +
                    ", to_station_name='" + to_station_name + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", arrive_time='" + arrive_time + '\'' +
                    ", train_class_name='" + train_class_name + '\'' +
                    ", day_difference='" + day_difference + '\'' +
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
                    '}';
        }
    }
}
