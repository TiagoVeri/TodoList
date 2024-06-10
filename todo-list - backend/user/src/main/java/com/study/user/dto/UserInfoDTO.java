package com.study.user.dto;

import com.study.user.entity.Address;
import com.study.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserInfoDTO {

    private String username;
    private String name;
    private String email;
    private Set<Address> address;
    private Set<Role> roles;
}
