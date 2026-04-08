package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.Settings;

@Mapper
public interface SettingsMapper {
    List<Settings> findAll();
}