package com.yuan.mapper;

import com.yuan.model.BlogComment;

import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentMapper {
    @Results(id="commentMap",value={
            @Result(property = "commentId",column = "comment_id"),
            @Result(property = "blogId",column = "blog_id"),
            @Result(property = "blogComment",column = "blog_comment"),
            @Result(property = "commentStatus",column = "comment_status"),
            @Result(property = "buildTime",column = "build_time")
    })
    @Select("select * from blogComment where blogId=#{blogId} and blog_status = 1")
    BlogComment getBlogCommentByBlogId(@Param("blogId")long blogId);

    @Insert("insert into blogComment(blog_id,blog_comment,comment_status) values(#{blogComment.blogId},#{blogComment.blogComment},1)")
    void saveBlogComment(@Param("blogComment")BlogComment blogComment);

    @Update("update blogComment set blog_status = 0 where comment_id=#{commentId}")
    void deleteBlogComment(@Param("commentId")int commentId);

}
