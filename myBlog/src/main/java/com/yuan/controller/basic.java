package com.yuan.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuan.model.UserLogin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class basic {
    @RequestMapping("/")
    public String Hello(UserLogin userLogin){
        System.out.println("Hello test!");
        return "editor";
    }

    @RequestMapping(value="userLogin", method=POST)
    public String loginI(@Valid UserLogin userLogin, Errors errors){
        if(errors.hasErrors())
            return "login";
        return "login";
    }
    @RequestMapping(value="userLogin", method=GET)
    public String loginIn(Model model){
        model.addAttribute(new UserLogin());
        return "login";
    }


}
