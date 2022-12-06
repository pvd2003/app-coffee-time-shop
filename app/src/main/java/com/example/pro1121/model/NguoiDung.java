package com.example.pro1121.model;

public class NguoiDung {
    private String idnguoidung;
    private String tennguoidung;
    private String sodienthoai;

    public String getIdnguoidung() {
        return idnguoidung;
    }

    public void setIdnguoidung(String idnguoidung) {
        this.idnguoidung = idnguoidung;
    }

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

    public NguoiDung(String idnguoidung, String tennguoidung, String sodienthoai) {
        this.idnguoidung = idnguoidung;
        this.tennguoidung = tennguoidung;
        this.sodienthoai = sodienthoai;


    }
}
