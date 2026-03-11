package com.example.mapper;

import com.example.model.UserData;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // ログインMapper
    UserData findByLoginIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    // 今の所使い道なし
    List<UserData> findAll();

    // user登録Mapper
    void insertUser(@Param("userId") String userId, @Param("password") String password);
}
