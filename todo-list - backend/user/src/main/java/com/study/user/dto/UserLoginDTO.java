package com.study.user.dto;

import com.study.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserLoginDTO {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

}
