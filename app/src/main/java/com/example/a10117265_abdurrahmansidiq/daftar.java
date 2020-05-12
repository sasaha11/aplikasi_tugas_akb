package com.example.a10117265_abdurrahmansidiq;

public class daftar {

    private String nim;
    private String nama;
    private String kelas;
    private String telepon;
    private String email;
    private String sosialmedia;

    public daftar()
    {

    }
    public String getNim() {
        return nim;
    }


    public void setNim(String nim) {
        this.nim = nim;
    }


    public String getNama() {
        return nama;
    }


    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getKelas() {
        return kelas;
    }


    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelepon() {
        return telepon;
    }


    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getSosialmedia() {
        return sosialmedia;
    }


    public void setSosialmedia(String sosialmedia) {
        this.sosialmedia = sosialmedia;
    }

    @Override
    public String toString()
    {
        return "NIM         : "+ nim+" \nNama      : "+ nama ;
    }
}
//12-05-2020,10117265,AbdurrahmanSidiq,IF-8