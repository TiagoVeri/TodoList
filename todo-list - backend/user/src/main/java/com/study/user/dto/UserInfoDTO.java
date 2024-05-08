package com.study.user.dto;

import com.study.user.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

    private String username;
    private String name;
    private String email;
    private Address address;
}
