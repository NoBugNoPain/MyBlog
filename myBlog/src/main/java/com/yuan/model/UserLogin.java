package com.yuan.model;

import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@PropertySource("classpath:ValidationMessages.properties")

public class UserLogin {
    private long userId;

    @NotNull
    @Size(min=5, max=20,message="{userName.size}")
    private String userName;
    @NotNull
    @Size(min=16, max=64,message="{passWord.size}")
    private String password;
    private int status;
    private Timestamp lastLoginTime;

    public UserLogin(){}

    UserLogin(long userId,String userName, String password, int status, Timestamp lastLoginTime) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
    }

    public UserLogin(String userName, String password) {
       this(0,userName,password,1,null);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserLogin{" +
                "UserId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
