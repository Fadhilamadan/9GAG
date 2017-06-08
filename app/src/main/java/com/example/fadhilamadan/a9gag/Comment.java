package com.example.fadhilamadan.a9gag;

/**
 * Created by Rama Adhiguna on 6/8/2017.
 */

public class Comment {
    private int id;
    private int posting_id;
    private String username;
    private String comment;

    public Comment(int id,int posting_id, String username, String comment) {
        this.setId(id);
        this.setPosting_id(posting_id);
        this.setUsername(username);
        this.setComment(comment);
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(int posting_id) {
        this.posting_id = posting_id;
    }
}
