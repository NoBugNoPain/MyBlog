package com.yuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.yuan.mapper.BlogMapper;
import com.yuan.model.Blog;
import com.yuan.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import com.yuan.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.yuan.model.UserLogin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class basic {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/")
    public String Hello(Model model){
        model.addAttribute("allBlogs",blogService.listAllBlogsService());
        return "index";
    }

    @RequestMapping("blog/{blogId}")
    public String openBlog(Model model, @PathVariable long blogId){
        model.addAttribute("Blog",blogService.searchBlogService(blogId));
        return "blog";
    }

    @RequestMapping(value="yuanBlog/userLogin", method=POST)
    public String loginI(@Valid UserLogin userLogin, Errors errors, HttpSession session){
        if(errors.hasErrors())
            return "login";
        if(userService.validUserLoginMessage(userLogin)) {
            session.setAttribute("loginUser",userLogin);
            return "redirect:/yuanBlog/BlogManager";
        }
        else{
            return "redirect:/yuanBlog/u    serLogin";
        }
    }
    @RequestMapping(value="yuanBlog/userLogin", method=GET)
    public String loginIn(Model model){
        model.addAttribute("loginUser",new UserLogin());
        return "login";
    }

    @RequestMapping(value="yuanBlog/userRegister",method = GET)
    public String registerIn(Model model){
        model.addAttribute(new UserLogin());
        return "register";
    }

    @RequestMapping(value="yuanBlog/userRegister",method = POST)
    public String getRegisterUser(@Valid UserLogin userLogin,Errors errors){
        if(errors.hasErrors())
            return "register";
        try{
            userService.saveUserLoginMessage(userLogin);
        }catch(Exception e){
            e.printStackTrace();
            //System.out.println("注册失败，请再试一遍！");
            return "register";
        }
        return "redirect:/";
    }

    @RequestMapping(value="yuanBlog/saveEditor",method = POST)
    @ResponseBody
    public String getSaveBlog(@RequestBody Blog blog){    //存储新创建的博客，或者存储修改后的博客

        Map<String,String> mp = new HashMap<>();
        if(!blogService.createBlogService(blog)) {
            mp.put("result", "fail");
        }
        else{
            mp.put("result","success");
        }
        //log.error(JSON.toJSONString(mp));
        return JSON.toJSONString(mp);
    }

    @RequestMapping(value="yuanBlog/BlogManager", method=GET)
    public String blogManager(Model model){
        model.addAttribute("allBlogs",blogService.listAllBlogsService());
        return "blogsManager";
    }


    @RequestMapping(value="delete/{blogId}",method = POST)
    public void deleteBlog(@PathVariable long blogId){
            log.error("成功删除"+blogId);
    }

    @RequestMapping(value="yuanBlog/editor/{blogId}",method = GET)
    public String editorOldBlog(Model model,@PathVariable long blogId){
        model.addAttribute("editorBlog",blogService.searchBlogService(blogId));
        return "editor";
    }

    @RequestMapping(value="yuanBlog/editor",method = GET)
    public String editorNewBlog(){
        return "editor";
    }

    @RequestMapping(value="upload",method = POST)
    @ResponseBody
    public String saveUploadImage(@RequestParam("multiple") MultipartFile[] multiple){
        List<String> picturesPosition = new LinkedList<String>();
        Map<String,String> result = new HashMap<>();
        if(multiple!=null&&multiple.length>0){
            for(int i = 0;i < multiple.length;i++){
                MultipartFile multipartFile = multiple[i];
                if(multipartFile!=null){
                    log.error(multipartFile.getContentType());
                    String filePath = FilenameUtils.concat("/upload/",multipartFile.getOriginalFilename());
                    try{
                        multipartFile.transferTo(new File(filePath));
                        picturesPosition.add(filePath);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        result.put("errno","0");
        result.put("data",JSON.toJSONString(picturesPosition));
        return JSON.toJSONString(result);
    }

}

