package com.example.pro1121.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private String tensp;
    private String giatien;

    public Sanpham() {
    }

    public Sanpham(String tensp, String giatien) {
        this.tensp = tensp;
        this.giatien = giatien;
    }

    public String getTenSP() {
        return tensp;
    }

    public void setTenSP(String tenloai) {
        this.tensp = tensp;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }
}
