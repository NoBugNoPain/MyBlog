package com.yuan.model;


import java.sql.Timestamp;

public class blogComment {
    private int commentId;
    private int blogId;
    private String blogComment;
    private int commentStatus;
    private Timestamp buildTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(String blogComment) {
        this.blogComment = blogComment;
    }

    public int getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Timestamp getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Timestamp buildTime) {
        this.buildTime = buildTime;
    }
}
