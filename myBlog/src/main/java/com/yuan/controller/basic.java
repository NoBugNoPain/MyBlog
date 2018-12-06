package com.yuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.yuan.model.Blog;
import com.yuan.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.yuan.model.UserLogin;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
@SessionAttributes("loginUser")
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
    public String loginI(@Valid UserLogin userLogin, Errors errors){
        if(errors.hasErrors())
            return "login";
        if(userService.validUserLoginMessage(userLogin)) {
            //log.error("成功");
        }
        return "login";
    }
    @RequestMapping(value="yuanBlog/userLogin", method=GET)
    public String loginIn(Model model){
        model.addAttribute("loginUser",new UserLogin());
        return "login";
    }

    @RequestMapping(value="yuanBlog/userRegister",method = GET)
    public String registerIn(Model model){
        model.addAttribute("loginUser",new UserLogin());
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
        return "redirect:/homePage";
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

}
