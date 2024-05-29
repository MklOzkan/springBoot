package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFound;
import com.tpe.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    /*
            In this class, we need to convert:
                1. User --> UserDetail
                2. Role --> GrantedAuthority
     */

    private final UserRepo userRepo;

//    public UserDetailServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //find username from database
        User foundUser = userRepo.findByUserName(username).orElseThrow(
                ()-> new ResourceNotFound("User name doent exist in DB")
        );

        if (foundUser!=null){
            return new
                    org.
                    springframework.
                    security.
                    core.
                    userdetails.
                    User(foundUser.
                    getUserName(),
                    foundUser.getPassword(),
                    //foundUser.getRoles()
                    buildGrantedAuthority(foundUser.getRoles())
                    );
        }else {
            throw new UsernameNotFoundException("User not found: " + username);
        }

    }
    private static List<SimpleGrantedAuthority> buildGrantedAuthority(final Set<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }
}
