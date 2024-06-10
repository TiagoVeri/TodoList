package com.study.user.controller;

import com.study.user.dto.LoginResponseDTO;
import com.study.user.dto.UserInfoDTO;
import com.study.user.dto.UserLoginDTO;
import com.study.user.dto.UserPasswordDTO;
import com.study.user.entity.Role;
import com.study.user.entity.UserProfile;
import com.study.user.security.CryptConfig;
import com.study.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final JwtEncoder jwtEncoder;
    private final CryptConfig passwordEncoder;

    public UserController(UserService userService, JwtEncoder jwtEncoder, CryptConfig passwordEncoder) {
        this.userService = userService;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserPasswordDTO userLogin){
        Optional<UserProfile> user = userService.getUserByUsername(userLogin.getUsername());
        if(user.isEmpty() || !user.get().isLoginCorrect(userLogin, passwordEncoder.passwordEncoder())){
            throw new BadCredentialsException("User or Password is invalid");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;

        String scopes = user.get().getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
    @PostMapping("/create")
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

    @DeleteMapping("{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDTO findUser(@PathVariable String username){
        return userService.findUser(username);

    }

    @GetMapping("/list-users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserInfoDTO> findAllUsers(@PageableDefault (sort = "username",
                                                            page = 0,
                                                            size = 10) Pageable pageable){
        return userService.findAllUsers(pageable);
    }
    //LOGOUT



}
