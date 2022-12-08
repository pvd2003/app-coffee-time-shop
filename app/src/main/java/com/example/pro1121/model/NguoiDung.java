package com.example.pro1121.model;

public class NguoiDung {
    private String tennguoidung;
    private String email;

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NguoiDung(String tennguoidung, String email) {
        this.tennguoidung = tennguoidung;
        this.email = email;


    }
}
