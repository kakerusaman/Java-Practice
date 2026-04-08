package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.LoginUserMapper;
import com.example.model.UserData;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    
    @Autowired
    LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserData user = loginUserMapper.findByLoginName(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("notUser"));
            
        return User.withUsername(user.getLoginId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
