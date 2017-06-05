package com.example.fadhilamadan.a9gag;

/**
 * Created by andre on 5/1/2017.
 */

public class Product {
    private int id;
    private String username;
    private String password;

    public Product( int id, String username, String password) {

        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPassword(String deskripsi) {
        this.password = password;
    }
}
