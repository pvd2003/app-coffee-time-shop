package com.example.pro1121.model;

public class GioHang {
    private String tensp;
    private String giasp;
    private String soluong;
    private int tongsp;

    public GioHang() {
    }

    public GioHang(String tensp, String giasp, String soluong, int tongsp) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.tongsp = tongsp;
    }

    public long getTongsp() {
        return tongsp ;
    }

    public void setTongsp(int tongsp) {
        this.tongsp = tongsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
