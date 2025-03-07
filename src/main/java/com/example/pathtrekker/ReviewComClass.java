package com.example.pathtrekker;

import java.util.ArrayList;

public class ReviewComClass {
    private String username;
    private String commentText;
    private int id;
    private int rating;
    private ArrayList<ReviewRepClass> replies;

    public ReviewComClass(String username,String commentText,int id,int review) {
        this.username = username;
        this.commentText = commentText;
        this.id = id;
        this.rating = review;
    }
    public void setReplies(ArrayList<ReviewRepClass> replies) {
        this.replies = replies;
    }

    public String getUsername() {
        return username;
    }
    public String getCommentText() {
        return commentText;
    }
    public int getId() {
        return id;
    }
    public int getRating() {
        return rating;
    }
    public ArrayList<ReviewRepClass> getReplies() {
        return replies;
    }

}
