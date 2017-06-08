package com.example.fadhilamadan.a9gag;

/**
 * Created by Rama Adhiguna on 6/8/2017.
 */

public class Username {
    private int id;
    private String username;
    private String password;

    public Username(int id, String username, String password) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
