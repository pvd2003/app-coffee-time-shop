package com.example.pro1121.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private String idsanpham;
    private String tensanpham;
    private String giasanpham;

    public Sanpham() {
    }

    public Sanpham(String tensanpham, String giasanpham) {
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
    }

    public String getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
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
