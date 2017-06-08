package com.example.fadhilamadan.a9gag;

/**
 * Created by Rama Adhiguna on 6/8/2017.
 */

public class Comment {
    private int id;
    private int username_id;
    private int posting_id;
    private String testing;
    private int upvite;

    public Comment(int id,int username_id,int posting_id, String text, int upvote) {
        this.setId(id);
        this.setUsername_id(id);
        this.setPosting_id(posting_id);
        this.setTesting(text);
        this.setUpvite(upvote);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsername_id() {
        return username_id;
    }

    public void setUsername_id(int username_id) {
        this.username_id = username_id;
    }

    public int getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(int posting_id) {
        this.posting_id = posting_id;
    }

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = testing;
    }

    public int getUpvite() {
        return upvite;
    }

    public void setUpvite(int upvite) {
        this.upvite = upvite;
    }
}
