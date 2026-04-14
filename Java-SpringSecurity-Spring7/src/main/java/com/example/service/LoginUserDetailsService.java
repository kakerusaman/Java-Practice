package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.LoginUserMapper;
import com.example.model.UserData;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginUserMapper loginUserMapper;

    public LoginUserDetailsService(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = loginUserMapper.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }
        return user;
    }

}
