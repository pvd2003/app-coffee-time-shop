package com.example.pro1121.model;

public class Loaisanpham {
    private  int maloai;
    private  String tenloai;

    public Loaisanpham() {
    }

    public Loaisanpham(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
