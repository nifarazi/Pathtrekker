package com.example.pathtrekker;

import java.util.List;

public class Photo {
    private int id;
    private String username;
    private byte[] image;
    private String caption;
    private int likeCount;
    private List<Comment> comments;

    public Photo(int id, String username, byte[] image, String caption, int likeCount) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.caption = caption;
        this.likeCount = likeCount;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public byte[] getImage() { return image; }
    public String getCaption() { return caption; }
    public int getLikeCount() { return likeCount; }
    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
}
