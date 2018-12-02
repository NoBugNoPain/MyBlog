package com.yuan.mapper;

import com.yuan.model.UserLogin;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from userLogin where user_name=#{userLogin.userName} and password = #{userLogin.password} and status = 1")
    @Results(id="userMapper", value={
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "lastLoginTime",column = "last_login_time")
    })
    List<UserLogin> searchUserByNameAndPassword(@Param("userLogin") UserLogin userLogin);


}
