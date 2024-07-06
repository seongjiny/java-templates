package com.dev.security.controller;

import com.dev.security.domain.UserDto;
import com.dev.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> testApi() {
        log.info("Testing API");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        userService.join(user.getUser_id(), user.getPassword());


        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
