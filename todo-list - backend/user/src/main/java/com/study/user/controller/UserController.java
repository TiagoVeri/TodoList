package com.study.user.controller;

import com.study.user.dto.UserInfoDTO;
import com.study.user.dto.UserLoginDTO;
import com.study.user.dto.UserPasswordDTO;
import com.study.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserLogin(@RequestBody UserLoginDTO userLoginDTO){
        userService.createUserLogin(userLoginDTO);
    }

    @PutMapping("/user-detail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserInfoDTO addDetailsToUser(@RequestBody UserInfoDTO infoDTO){
        return userService.addDetailsToUser(infoDTO);
    }

    @PutMapping("/password-change")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody UserPasswordDTO passwordDTO){
        userService.changePassword(passwordDTO);
    }
    //Create DELETE

    @DeleteMapping("{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
    //FIND by ID
    //FIND All (admin)
    //LOGOUT



}
