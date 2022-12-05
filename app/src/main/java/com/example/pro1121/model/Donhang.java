package com.example.pro1121.model;

public class Donhang {
    private int madonhang;
    private String tentk;
    private String email;
    private String tensp;
    private int soluong;

    public Donhang() {
    }

    public Donhang(int madonhang, String tentk, String email, String tensp, int soluong) {
        this.madonhang = madonhang;
        this.tentk = tentk;
        this.email = email;
        this.tensp = tensp;
        this.soluong = soluong;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
