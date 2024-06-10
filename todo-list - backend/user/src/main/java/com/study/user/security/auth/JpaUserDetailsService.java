package com.study.user.security.auth;

import com.study.user.entity.UserProfile;
import com.study.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userRepository.findByUsername(username).orElseThrow(com.study.user.exceptions.UsernameNotFoundException::new);

        //TODO edit this when roles were being added
        List<GrantedAuthority> authorities = Collections.emptyList();

        return new User(userProfile.getUsername(), userProfile.getPassword(), authorities );
    }
}
