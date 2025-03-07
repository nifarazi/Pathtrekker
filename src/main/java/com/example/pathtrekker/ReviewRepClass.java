package com.example.pathtrekker;

public class ReviewRepClass {
    private int comment_id;
    private String Rusername;
    private String reply;

    public ReviewRepClass(String username,String reply,int comment_id) {
        this.Rusername = username;
        this.reply = reply;
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return Rusername;
    }
    public String getReply() {
        return reply;
    }
    public int getComment_id() {
        return comment_id;
    }
}
