package com.example.pro1121.model;

public class LichSu {
    private String idlichsu;
    private String time;
    private String trangthai;
    private String tong;

    public LichSu() {
    }

    public LichSu(String idlichsu, String time, String trangthai, String tong) {
        this.idlichsu = idlichsu;
        this.time = time;
        this.trangthai = trangthai;
        this.tong = tong;
    }

    public String getIdlichsu() {
        return idlichsu;
    }

    public void setIdlichsu(String idlichsu) {
        this.idlichsu = idlichsu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
    }
}
