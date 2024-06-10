package com.study.user.mapper;

import com.study.user.dto.UserInfoDTO;
import com.study.user.dto.UserLoginDTO;
import com.study.user.dto.UserPasswordDTO;
import com.study.user.entity.UserProfile;


public class UserMapper {

    private UserMapper(){}
    public static UserProfile userLoginDTOToUser(UserLoginDTO loginDTO){
        UserProfile user = new UserProfile();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        user.setRoles(loginDTO.getRoles());
        return user;
    }

    public static UserLoginDTO userToUserLoginDTO(UserProfile user){
        UserLoginDTO userDTO = new UserLoginDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public static UserInfoDTO userToUserInfoDTO(UserProfile user){
        UserInfoDTO userDTO = new UserInfoDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setAddress(user.getAddress());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public static UserProfile userInfoDTOToUser(UserInfoDTO infoDTO, UserProfile user){
        user.setName(infoDTO.getName());
        user.setEmail(infoDTO.getEmail());
        user.setUsername(infoDTO.getUsername());
        user.setAddress(infoDTO.getAddress());
        user.setRoles(infoDTO.getRoles());
        return user;
    }


    public static UserProfile userPasswordDTOToUser(UserPasswordDTO passwordDTO, UserProfile profile){
        profile.setPassword(passwordDTO.getPassword());
        return profile;
    }
}
