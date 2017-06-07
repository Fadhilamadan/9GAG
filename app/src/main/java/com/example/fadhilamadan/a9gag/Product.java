package com.example.fadhilamadan.a9gag;

public class Product {
    private String nama;
    private int id;
    private String password;


    public Product(int id,String nama,String password) {
        this.setId(id);
        this.setNama(nama);
        this.setPassword(getPassword());
    }



    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}