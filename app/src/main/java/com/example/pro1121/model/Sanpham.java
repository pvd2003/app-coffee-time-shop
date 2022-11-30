package com.example.pro1121.model;

public class Sanpham {
    private String tenloai;
    private String giatien;

    public Sanpham() {
    }

    public Sanpham(String tenloai, String giatien) {
        this.tenloai = tenloai;
        this.giatien = giatien;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }
}
