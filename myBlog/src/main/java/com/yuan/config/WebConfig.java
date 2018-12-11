package com.yuan.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan("com.yuan.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){       //上传图片文件处理器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100*1024*1024);
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        return commonsMultipartResolver;
    }

    //处理静态资源，是使前端可以加载js和image文件夹下的静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
        registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/views/image/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/views/fonts/");
        registry.addResourceHandler("/upload/**").addResourceLocations("/WEB-INF/upload/");

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }


    //使用fastjson来处理json
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(0,converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加新的拦截器，可以添加多个
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new ValidLoginIntercepter());
        interceptorRegistration.addPathPatterns("/yuanBlog/**");
        interceptorRegistration.excludePathPatterns("/yuanBlog/userLogin");
        interceptorRegistration.excludePathPatterns("/yuanBlog/userRegister");
        super.addInterceptors(registry);
    }

    @Bean
    public JSONPResponseBodyAdvice jsonpResponseBodyAdvice(){
        return new JSONPResponseBodyAdvice();
    }

}
