package com.dev.security.service;

import com.dev.security.domain.UserDto;
import com.dev.security.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userMapper.findByUserId(username);
        log.info("Found user: {}", user);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            return new User(user.getUser_id(), user.getPassword(), authorities);
        }

        return null;
    }

    @Transactional
    public boolean join(String userId, String userPwd) {
        UserDto user = userMapper.findByUserId(userId);

        if (user != null){
            return false;
        }
        user = new UserDto();
        user.setUser_id(userId);
        user.setPassword(encoder.encode(userPwd));
        int result = userMapper.save(user);
        if (result > 0) {
            log.info("User saved successfully");
        }
        return true;
    }
}
