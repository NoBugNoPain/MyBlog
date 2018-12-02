package com.yuan.service;

import com.yuan.mapper.UserMapper;
import com.yuan.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public boolean validUserLoginMessage(UserLogin userLogin){
        List<UserLogin> lUserLogin= userMapper.searchUserByNameAndPassword(userLogin);
        if(lUserLogin == null || lUserLogin.size()!=1)
            return false;
        else
        {
            for(UserLogin ul:lUserLogin){
                System.out.println(ul);
            }
            return true;
        }
    }
}
