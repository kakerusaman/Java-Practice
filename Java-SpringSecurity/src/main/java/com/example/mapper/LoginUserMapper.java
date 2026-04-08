package com.example.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.UserData;

@Mapper
public interface LoginUserMapper {

    // ログインIDの取得 OptionalはログインIDがnullかもしれないので
    Optional<UserData> findByLoginName(@Param("loginId") String loginId);

}
