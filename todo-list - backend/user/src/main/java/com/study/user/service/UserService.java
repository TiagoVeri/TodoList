package com.study.user.service;

import com.study.user.dto.UserInfoDTO;
import com.study.user.dto.UserLoginDTO;
import com.study.user.dto.UserPasswordDTO;
import com.study.user.entity.UserProfile;
import com.study.user.exceptions.SameUsernameException;
import com.study.user.exceptions.UsernameNotFoundException;
import com.study.user.mapper.UserMapper;
import com.study.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.study.user.mapper.UserMapper.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUserLogin(UserLoginDTO userLoginDTO){
        if(getUserByUsername(userLoginDTO.getUsername()).isPresent()){
            throw new SameUsernameException();
        }
        UserProfile user = userLoginDTOToUser(userLoginDTO);
        userRepository.save(user);
    }

    public void changePassword(UserPasswordDTO passwordDTO){
        Optional<UserProfile> userOptional = getUserByUsername(passwordDTO.getUsername());
        if(userOptional.isPresent()){
            UserProfile user = userPasswordDTOToUser(passwordDTO, userOptional.get());
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public UserInfoDTO addDetailsToUser(UserInfoDTO infoDTO){
        Optional<UserProfile> userOptional = getUserByUsername(infoDTO.getUsername());
        if(userOptional.isPresent()){
            UserProfile user = userInfoDTOToUser(infoDTO, userOptional.get());
            return UserMapper.userToUserInfoDTO(userRepository.save(user));
        } else{
            throw new UsernameNotFoundException();
        }
    }

    public void deleteUser(String username){
        Optional<UserProfile> userProfile = getUserByUsername(username);
//        if(userProfile.isPresent()){
//
//        }
    }

    public Optional<UserProfile> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
