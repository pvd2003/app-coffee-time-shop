package com.example.pro1121.model;

public class NguoiDung {
    private String tennguoidung;
    private String sodienthoai;

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public NguoiDung( String tennguoidung, String sodienthoai) {
        this.tennguoidung = tennguoidung;
        this.sodienthoai = sodienthoai;


    }
}
