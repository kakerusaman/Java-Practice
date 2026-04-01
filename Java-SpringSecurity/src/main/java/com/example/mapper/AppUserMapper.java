package com.example.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.UserData;

@Mapper
public interface AppUserMapper {
    // Optionalは値が入ってくる場合もあるし、ない場合もある。
    // springsecurityでユーザー名が拾えない場合もあるので
    Optional<UserData> findByUserName(@Param("userId") String userId);
}
