package com.example.pro1121.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private String tensanpham;
    private String giasanpham;

    public Sanpham() {
    }

    public Sanpham(String tensanpham, String giasanpham) {
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
    }

    public String getTenSP() {
        return tensanpham;
    }

    public void setTenSP(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGiatien() {
        return giasanpham;
    }

    public void setGiatien(String giasanpham) {
        this.giasanpham = giasanpham;
    }
}
