package com.study.user.dto;

import com.study.user.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    private Long id;
    private String username;
    private String password;
    private Roles role;

}
