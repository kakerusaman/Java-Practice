package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.UserData;

@Mapper
public interface LoginUserMapper {

    UserData findByLoginName(@Param("loginId") String loginId);

}
