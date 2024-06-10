package com.study.user.service;

import com.study.user.dto.UserInfoDTO;
import com.study.user.dto.UserLoginDTO;
import com.study.user.dto.UserPasswordDTO;
import com.study.user.entity.UserProfile;
import com.study.user.exceptions.SameUsernameException;
import com.study.user.exceptions.UsernameNotFoundException;
import com.study.user.mapper.UserMapper;
import com.study.user.repository.UserRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.study.user.mapper.UserMapper.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUserLogin(UserLoginDTO userLoginDTO){
        if(getUserByUsername(userLoginDTO.getUsername()).isPresent()){
            throw new SameUsernameException();
        }
        UserProfile user = userLoginDTOToUser(userLoginDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void changePassword(UserPasswordDTO passwordDTO){
        Optional<UserProfile> userOptional = getUserByUsername(passwordDTO.getUsername());
        if(userOptional.isPresent()){
            UserProfile user = userPasswordDTOToUser(passwordDTO, userOptional.get());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Transactional
    @Modifying
    public void deleteUser(String username){
        Optional<UserProfile> userProfile = getUserByUsername(username);
        if(userProfile.isPresent()){
            try{
               userRepository.deleteByUsername(username);
            } catch (RuntimeException e){
                throw new DataAccessResourceFailureException("Something went wrong deleting the username");
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public Optional<UserProfile> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserInfoDTO findUser(String username){
        Optional<UserProfile> userOptional = getUserByUsername(username);
        if(userOptional.isPresent()){
            return UserMapper.userToUserInfoDTO(userOptional.get());
        } else{
            throw new UsernameNotFoundException();
        }
    }

    public Page<UserInfoDTO> findAllUsers(Pageable pageable){
         return userRepository.findAll(pageable)
                                    .map(UserMapper::userToUserInfoDTO);
    }
}
