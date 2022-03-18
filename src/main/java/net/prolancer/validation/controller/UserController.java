package net.prolancer.validation.controller;

import net.prolancer.validation.common.entity.ResponseHandler;
import net.prolancer.validation.entity.User;
import net.prolancer.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseHandler.ok("Successfully created", HttpStatus.CREATED, savedUser);
    }
}
