package com.study.user.entity;

import com.study.user.dto.UserPasswordDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity
@Getter @Setter
public class UserProfile extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn (name = "id"),
            inverseJoinColumns = @JoinColumn(name = "addressId")
    )
    private Set<Address> address;

    private String username;
    
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")

    )
    private Set<Role> roles;

    public boolean isLoginCorrect(UserPasswordDTO userLogin, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userLogin.getPassword(), this.password);
    }


    // usar o input encriptografado, ou seja, encriptogravar a senha no ato do login. ver sobre Salt.
}
