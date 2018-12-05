package com.yuan.mapper;

import com.yuan.model.UserLogin;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    //@Update("update userLogin set last_login_time = now() where user_name = #{userLogin.userName}")
    @Select("select * from userLogin where user_name=#{userLogin.userName} and password = #{userLogin.password} and status <> 0")
    @Results(id="userMapper", value={
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "lastLoginTime",column = "last_login_time")
    })
    List<UserLogin> searchUserByNameAndPassword(@Param("userLogin") UserLogin userLogin);

    @Update("update userLogin set last_login_time = now() where user_name = #{userLogin.userName}")
    void updateLoginTime(@Param("userLogin") UserLogin userLogin);

    @Insert("insert into userLogin(user_id,user_name,password,status,last_login_time) values(#{userLogin.userId},#{userLogin.userName},#{userLogin.password},2,now())")
    void saveUserLoginMessage(@Param("userLogin") UserLogin userLogin);

    /*测试一个
    *@Insert中能否执行两个sql语句，结果是成功的可以执行多个sql语句，但是需要在前几个update语句中加入分号;而且好像需要在databaseurl后加入&allowMultiQueries=true
    @Insert(value={"insert into userLogin(user_id,user_name,password,status,last_login_time) values(#{userLogin.userId},#{userLogin.userName},#{userLogin.password},2,now());",
            "insert into userLogin(user_id,user_name,password,status,last_login_time) values(12614,#{userLogin.userName},#{userLogin.password},2,now())"
    })
    void saveUserLoginMessage(@Param("userLogin") UserLogin userLogin);*/


}
