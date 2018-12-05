package com.yuan.service;

import com.yuan.mapper.UserMapper;
import com.yuan.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    private static int no = 0;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static LocalDate ldt = LocalDate.now();

    @Transactional
    public boolean validUserLoginMessage(UserLogin userLogin){
        List<UserLogin> lUserLogin= userMapper.searchUserByNameAndPassword(userLogin);
        if(lUserLogin == null || lUserLogin.size()!=1)
            return false;
        else
        {
            for(UserLogin ul:lUserLogin){
                userMapper.updateLoginTime(userLogin);
                System.out.println(ul);
            }
            return true;
        }
    }

    public void saveUserLoginMessage(UserLogin userLogin) throws Exception{
        /*用户编号组成为1开头加年份日期加位数数
        * 四位数:如果在同一天注册的用户，no++左补齐0
        * 如果第二天no重置为0重新开始计算
        * */
        String userNo = "";
        if(ldt.equals(LocalDate.now())){
            userNo = "1" + ldt.format(fmt)+String.format("%04d",++no);
        }else{
            ldt = LocalDate.now();
            no = 0;
            userNo = "1" + ldt.format(fmt)+String.format("%04d",++no);
        }
        userLogin.setUserId(Long.parseLong(userNo));
        userMapper.saveUserLoginMessage(userLogin);

    }
}
