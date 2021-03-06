package com.yuan.mapper;

import com.yuan.model.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BlogMapper {
    @Results(id="blogMap", value={
            @Result(property = "blogId",column = "blog_id"),
            @Result(property = "blogName",column = "blog_name"),
            @Result(property = "blogContent",column = "blog_essay"),
            @Result(property = "comment",column = "blog_id",many=@Many(select="com.yuan.mapper.CommentMapper.getBlogCommentByBlogId")),
            @Result(property = "blogStatus",column = "blog_status"),
            @Result(property = "buildTime",column = "build_time")
    })
    @Select("select * from blogName bn,blogContent bc where bn.blog_id = bc.blog_id and bn.blog_status = 1")
    List<Blog> selectAllBlogs();

    @ResultMap(value="blogMap")
    @Select("select * from blogName bn,blogContent bc where bn.blog_id = bc.blog_id and bn.blog_id = #{blogId} and bn.blog_status = 1")
    Blog selectBlogById(@Param("blogId")long blogId);

    @ResultMap(value="blogMap")
    @Select("select * from blogName bn,blogContent bc where bn.blog_name like concat('%'+#{keyword}+'%') and bn.blog_status = 1 and bn.blog_id = bc.blog_id")
    List<Blog> selectBlogByNameKeyWord(@Param("keyword") String keyword);

    @Insert("insert into blogName(blog_id,blog_name,blog_status,build_time) values(#{blog.blogId},#{blog.blogName},1,now())")
    void InsertBlogName(@Param("blog")Blog blog);

    @Insert("insert into blogContent(blog_id,blog_essay) values(#{blog.blogId},#{blog.blogContent})")
    void InsertBLogContent(@Param("blog")Blog blog);

    @Update("update blogName set blog_name = #{blog.blogName} where blog_id = #{blog.blogId} and blog_status = 1")
    void updateBlogName(@Param("blog")Blog blog);


    @Update("update blogContent set blog_essay = #{blog.blogContent} where blog_id = #{blog.blogId}")
    void updateBlogEssay(@Param("blog")Blog blog);

    @Update("update blogName set blog_status = 0 where blog_id = #{blogId}")
    void deleteBlogById(@Param("blogId")long blogId);

}
