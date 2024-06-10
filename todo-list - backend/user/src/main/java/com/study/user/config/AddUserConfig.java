package com.study.user.config;

import com.study.user.entity.Address;
import com.study.user.entity.Role;
import com.study.user.entity.UserProfile;
import com.study.user.repository.RoleRepository;
import com.study.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class AddUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AddUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setRoleName(Role.Roles.ADMIN.name());
        role.setRoleId(1L);

        Role role2 = new Role();
        role2.setRoleName(Role.Roles.USER.name());
        role2.setRoleId(2L);

        List<Role> roles = roleRepository.saveAll(Arrays.asList(role, role2));

        Address address = new Address();
        address.setStreet("Rua das Flores");
        address.setNumber(123L);
        address.setCity("São Paulo");
        address.setCountry("Brasil");

        // Criar instância de UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setName("João Silva");
        userProfile.setEmail("joao@example.com");
        userProfile.setUsername("joaosilva");
        userProfile.setPassword(passwordEncoder.encode("123"));
        userProfile.setRoles(new HashSet<>(roles));

        // Adicionar Address ao UserProfile
        Set<Address> addresses = new HashSet<>();
        addresses.add(address);
        userProfile.setAddress(addresses);

        userRepository.save(userProfile);

    }

}
