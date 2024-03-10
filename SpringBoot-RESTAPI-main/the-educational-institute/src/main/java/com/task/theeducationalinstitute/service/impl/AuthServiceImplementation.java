package com.task.theeducationalinstitute.service.impl;

import com.task.theeducationalinstitute.model.AppUser;
import com.task.theeducationalinstitute.repository.AppUserRepo;
import com.task.theeducationalinstitute.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;


    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String signUp(String name, String username, String password) {
        //Check whether user already exists or not
        if(appUserRepo.existsByUsername(username)){
            throw new RuntimeException("User already exists");
        }

        //Encode password
        var encodedPassword = passwordEncoder.encode(password);

        //Authorities
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //create App user
       var appUser = AppUser.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .authorities(authorities)
                .build();

        //save user
        appUserRepo.save(appUser);

        //Generate Token
        return JwtUtils.generateToken(username);
    }
}
