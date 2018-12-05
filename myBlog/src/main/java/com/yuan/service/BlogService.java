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

    @Transactional
    public boolean createBlogService(Blog blog){
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
        }
        return true;
    }
}
