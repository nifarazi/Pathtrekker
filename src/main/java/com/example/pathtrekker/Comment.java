package com.example.pathtrekker;

public class Comment {
    private String username;
    private String commentText;

    public Comment(String username, String commentText) {
        this.username = username;
        this.commentText = commentText;
    }

    public String getUsername() { return username; }
    public String getCommentText() { return commentText; }
}
