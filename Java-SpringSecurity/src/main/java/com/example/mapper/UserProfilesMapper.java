package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.UserProfiles;

@Mapper
public interface UserProfilesMapper {
    
    // ユーザー情報登録
    void insertUserProfiles(@Param("id") String id, @Param("lastName") String lastName, @Param("firstName") String firstName, @Param("lastNameKana") String lastNameKana, @Param("firstNameKana") String firstNameKana,
                            @Param("gender") int gender, @Param("zipCode") String zipCode, @Param("address") String address, @Param("streetNumber") String streetNumber, @Param("addressKana") String addressKana,
                            @Param("streetNumberKana") String streetNumberKana, @Param("birthDate") String birthData, @Param("phoneNumber") String phoneNumber, @Param("emergencyName") String emergencyName, @Param("emergencyPhone") String emaergencyPhone);           

    // ユーザー情報取得
    UserProfiles findUser(@Param("id") String id);

    // ユーザー情報更新
    void updateUserProfiles(@Param("id") String id, @Param("lastName") String lastName, @Param("firstName") String firstName, @Param("lastNameKana") String lastNameKana, @Param("firstNameKana") String firstNameKana,
                            @Param("gender") int gender, @Param("zipCode") String zipCode, @Param("address") String address, @Param("streetNumber") String streetNumber, @Param("addressKana") String addressKana,
                            @Param("streetNumberKana") String streetNumberKana, @Param("birthDate") String birthDate, @Param("phoneNumber") String phoneNumber, @Param("emergencyName") String emergencyName, @Param("emergencyPhone") String emergencyPhone);
    
}
