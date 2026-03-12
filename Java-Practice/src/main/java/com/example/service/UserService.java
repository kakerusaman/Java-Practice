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

    public UserData findUser(String userId){
        return userMapper.findUser(userId);
    }

    public List<UserData> findAll(){
        return userMapper.findAll();
    }

    public void insertUser(String userId, String password){
        String hashPass = passwordEncoder.encode(password);

        userMapper.insertUser(userId, hashPass);
    }
}
