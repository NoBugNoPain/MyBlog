package com.yuan.model;

import java.util.List;
import java.sql.Timestamp;
import java.util.Objects;

public class Blog {
    long BlogId;
    String BlogName;     //博客标题
    String BlogContent;  //博客文章
    List<BlogComment> comment;  //博客评论
    int blogStatus;          //博客状态
    Timestamp buildTime;   //博客创建时间

    public long getBlogId() {
        return BlogId;
    }

    public void setBlogId(long blogId) {
        BlogId = blogId;
    }

    public String getBlogName() {
        return BlogName;
    }

    public void setBlogName(String blogName) {
        BlogName = blogName;
    }

    public String getBlogContent() {
        return BlogContent;
    }

    public void setBlogContent(String blogContent) {
        BlogContent = blogContent;
    }

    public List<BlogComment> getComment() {
        return comment;
    }

    public void setComment(List<BlogComment> comment) {
        this.comment = comment;
    }

    public Timestamp getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Timestamp buildTime) {
        this.buildTime = buildTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blog)) return false;
        Blog blog = (Blog) o;
        return BlogId == blog.BlogId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(BlogId);
    }
}
