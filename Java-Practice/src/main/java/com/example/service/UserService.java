package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.mapper.UserMapper;
import com.example.model.UserData;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserData checkLoginData(String userId, String password){
        return userMapper.findByLoginIdAndPassword(userId, password);
    }

    public List<UserData> findAll(){
        return userMapper.findAll();
    }

    public void insertUser(String userId, String password){
        String hashPass = passwordEncoder.encode(password);

        userMapper.insertUser(userId, hashPass);
    }
}
