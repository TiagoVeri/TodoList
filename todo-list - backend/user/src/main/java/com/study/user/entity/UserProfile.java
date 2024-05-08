package com.study.user.entity;

import com.study.user.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserProfile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private String username;
    
    private String password;

    private Roles role;

    //TODO add crypt for password in setter method, não decriptografar para verificar o password,
    // usar o input encriptografado, ou seja, encriptogravar a senha no ato do login. ver sobre Salt.
}
