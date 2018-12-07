package com.yuan.config;

import com.yuan.model.UserLogin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidLoginIntercepter implements HandlerInterceptor{

    /*重写拦截器来检测登录博客管理后台时是否登录过用户*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){
            response.sendRedirect("/yuanBlog/userLogin");
            return false;
        }else if(user instanceof UserLogin){
            UserLogin userLogin = (UserLogin)user;
            if(userLogin.getUserName()==null||userLogin.getPassword()==null){     //成功登录的用户的status一定是从数据库中获得了1或者2
                response.sendRedirect("/yuanBlog/userLogin");
                return false;
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{

    }

}
