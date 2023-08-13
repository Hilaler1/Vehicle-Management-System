package com.mobiliz.userservice.config.security;

import com.mobiliz.userservice.entity.User;
import com.mobiliz.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {

        User user=userService.findById(id);

            List<GrantedAuthority> authorityList=new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getName())
                    .password(user.getPassword())
                    .accountExpired(false)
                    .accountLocked(false)
                    .authorities(authorityList)
                    .build();



    }
}
