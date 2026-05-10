package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfiles {
    private String id;

    private String lastName;

    private String firstName;

    private String lastNameKana;

    private String firstNameKana;

    private int gender;

    private String zipCode;

    private String address;

    private String streetNumber;

    private String addressKana;

    private String streetNumberKana;

    private String birthDate;

    private String phoneNumber;

    private String emergencyName;

    private String emergencyPhone;

}
