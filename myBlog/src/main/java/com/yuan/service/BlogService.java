package com.yuan.service;

import com.yuan.mapper.BlogMapper;
import com.yuan.mapper.CommentMapper;
import com.yuan.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/*
* 博客相关服务，如展示主页博客，创建，修改，删除博客
* */
@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    CommentMapper commentMapper;

    private static int no = 0;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static LocalDate ldt = LocalDate.now();

    public List<Blog> listAllBlogsService(){
        try{
            return blogMapper.selectAllBlogs();
        }catch(Exception e){
            e.printStackTrace();
            log.error("列出所有博客失败!");
            return null;
        }
    }

    public Blog searchBlogService(long blogId){
        try{
            Blog blog = blogMapper.selectBlogById(blogId);
            return blog;
        }
        catch(Exception e){
            e.printStackTrace();
            log.error("打开博客失败");
            return null;
        }
    }

    @Transactional
    public boolean createBlogService(Blog blog){
        if(blog.getBlogId()==0){       //判断是否为新创建的博客
            String blogNo = "";
            if(ldt.equals(LocalDate.now())){
                blogNo = "1" + ldt.format(fmt)+String.format("%04d",++no);
            }else{
                ldt = LocalDate.now();
                no = 0;
                blogNo = "2" + ldt.format(fmt)+String.format("%04d",++no); //博客编号与用户基本相同除了开头为2
            }
            blog.setBlogId(Long.parseLong(blogNo));
            try{
                blogMapper.InsertBlogName(blog);
                blogMapper.InsertBLogContent(blog);
            }catch(Exception e){
                e.printStackTrace();
                log.error("存储新建博客失败");
                return false;
            }
        }
        else{                           //保存修改的博客
            try{
                blogMapper.updateBlogName(blog);
                blogMapper.updateBlogEssay(blog);
            }catch(Exception e){
                e.printStackTrace();
                log.error("保存修改博客失败");
                return false;
            }
        }
        return true;
    }

    public boolean DeleteBlogService(long id){
        try{
            blogMapper.deleteBlogById(id);
        }catch(Exception e){
            e.printStackTrace();
            log.error("删除博客失败");
            return false;
        }
        return true;
    }
}
