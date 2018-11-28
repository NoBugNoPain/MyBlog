package com.yuan.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuan.model.UserLogin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class basic {
    @RequestMapping("MyBlog")
    public String Hello(UserLogin userLogin){
        System.out.println("Hello test!");
        return "login";
    }

    @RequestMapping(value="/login/user", method=POST)
    public String loginIn(@Valid UserLogin userLogin, Errors errors){
        if(errors.hasErrors())
            return "login";
        return "index";
    }
}
