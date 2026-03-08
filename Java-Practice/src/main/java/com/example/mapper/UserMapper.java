package com.example.mapper;

import com.example.model.UserData;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserData findByLoginIdAndPassword(@Param("userId") String userId, @Param("password")String password);

    List<UserData> findAll();
}
