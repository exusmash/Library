package com.example.springproject.filmLibrary.service.userdetails;

import com.example.springproject.filmLibrary.constants.UserRolesConstants;
import com.example.springproject.filmLibrary.model.User;
import com.example.springproject.filmLibrary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.ADMIN)), adminUserName, null);
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            //ROLE_USER, ROLE_FILMER
            authorities.add(new SimpleGrantedAuthority(user.getRole().getId() == 1L
                    ? "ROLE_" + UserRolesConstants.USER
                    : "ROLE_" + UserRolesConstants.FILMER));

            return new CustomUserDetails(user.getPassword(), authorities, username, user.getId());
        }

    }
}

