package com.hjianfei.ticket.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016-7-17.
 */
public class TicketMoney implements Serializable {

    /**
     * reason : 请求成功
     * result : {"gr":"-","qt":"-","rw":"¥267.0","rz":"-","tz":"-","wz":"¥92.0","yw":"¥174.0","yz":"¥92.0","ze":"-","zy":"-","swz":"-"}
     * error_code : 0
     */

    private String reason;
    /**
     * gr : -
     * qt : -
     * rw : ¥267.0
     * rz : -
     * tz : -
     * wz : ¥92.0
     * yw : ¥174.0
     * yz : ¥92.0
     * ze : -
     * zy : -
     * swz : -
     */

    private ResultMoney result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultMoney getResult() {
        return result;
    }

    public void setResult(ResultMoney result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultMoney implements Serializable {
        private String gr;
        private String qt;
        private String rw;
        private String rz;
        private String tz;
        private String wz;
        private String yw;
        private String yz;
        private String ze;
        private String zy;
        private String swz;

        public String getGr() {
            return gr;
        }

        public void setGr(String gr) {
            this.gr = gr;
        }

        public String getQt() {
            return qt;
        }

        public void setQt(String qt) {
            this.qt = qt;
        }

        public String getRw() {
            return rw;
        }

        public void setRw(String rw) {
            this.rw = rw;
        }

        public String getRz() {
            return rz;
        }

        public void setRz(String rz) {
            this.rz = rz;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getWz() {
            return wz;
        }

        public void setWz(String wz) {
            this.wz = wz;
        }

        public String getYw() {
            return yw;
        }

        public void setYw(String yw) {
            this.yw = yw;
        }

        public String getYz() {
            return yz;
        }

        public void setYz(String yz) {
            this.yz = yz;
        }

        public String getZe() {
            return ze;
        }

        public void setZe(String ze) {
            this.ze = ze;
        }

        public String getZy() {
            return zy;
        }

        public void setZy(String zy) {
            this.zy = zy;
        }

        public String getSwz() {
            return swz;
        }

        public void setSwz(String swz) {
            this.swz = swz;
        }

        @Override
        public String toString() {
            return "ResultMoney{" +
                    "gr='" + gr + '\'' +
                    ", qt='" + qt + '\'' +
                    ", rw='" + rw + '\'' +
                    ", rz='" + rz + '\'' +
                    ", tz='" + tz + '\'' +
                    ", wz='" + wz + '\'' +
                    ", yw='" + yw + '\'' +
                    ", yz='" + yz + '\'' +
                    ", ze='" + ze + '\'' +
                    ", zy='" + zy + '\'' +
                    ", swz='" + swz + '\'' +
                    '}';
        }
    }
}
