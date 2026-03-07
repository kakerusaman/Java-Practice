package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mapper.UserMapper;
import com.example.model.UserData;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public UserData checkLoginData(String userId, String password){
        return userMapper.findByLoginIdAndPassword(userId, password);
    }
}
