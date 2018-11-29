package com.yuan.model;

import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@PropertySource("classpath:ValidationMessages.properties")

public class UserLogin {
    String UserId;

    @NotNull
    @Size(min=5, max=20,message="测试在这种情况")
    String userName;
    @NotNull
    @Size(min=6, max=20,message="{passWord.size}")
    String password;
    int status;
    Timestamp lastLoginTime;

    public UserLogin(){}

    UserLogin(String userId,String userName, String password, int status, Timestamp lastLoginTime) {
        UserId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
    }

    public UserLogin(String userName, String password) {
       this(null,userName,password,1,null);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
