package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {
    @Resource
    private com.example.demo.dao.UserMapper UserMapper;
    public User loginVerification(String username){
        UserExample example=new UserExample();
        example.or().andLoginNameEqualTo(username).andRoleIdEqualTo("1");
        List<User> users=UserMapper.selectByExample(example);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}