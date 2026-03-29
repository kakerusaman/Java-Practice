package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.AppUserMapper;
import com.example.model.UserData;

@Service
public class AppUserDetailsService implements UserDetailsService{

    @Autowired
    AppUserMapper appUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserData user = appUserMapper.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		return User.withUsername(user.getUserId())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}

}
