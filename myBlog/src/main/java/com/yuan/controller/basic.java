package com.yuan.controller;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuan.model.UserLogin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class basic {

    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String Hello(UserLogin userLogin){
        System.out.println("Hello test!");
        return "editor";
    }

    @RequestMapping(value="userLogin", method=POST)
    public String loginI(@Valid UserLogin userLogin, Errors errors){
        if(errors.hasErrors())
            return "login";
        if(userService.validUserLoginMessage(userLogin)) {
            //log.error("成功");
        }
        return "login";
    }
    @RequestMapping(value="userLogin", method=GET)
    public String loginIn(Model model){
        model.addAttribute(new UserLogin());
        return "login";
    }

    @RequestMapping(value="userRegister",method = GET)
    public String registerIn(Model model){
        model.addAttribute(new UserLogin());
        return "register";
    }

    @RequestMapping(value="userRegister",method = POST)
    public String getRegisterUser(@Valid UserLogin userLogin,Errors errors){
        if(errors.hasErrors())
            return "register";
        try{
            userService.saveUserLoginMessage(userLogin);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("注册失败，请再试一遍！");
            return "register";
        }
        return "register";
    }

    @RequestMapping(value="saveEditor",method = POST)
    public void getSaveBlo(){
        System.out.println("成功");
        //log.error("成功");
    }
}
