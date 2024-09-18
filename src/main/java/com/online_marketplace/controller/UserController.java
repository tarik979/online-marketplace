package com.online_marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.model.LocalUser;
import com.online_marketplace.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<LocalUser>> findAllUsers(){
        List<LocalUser> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<LocalUser> findUserById(@PathVariable Long id){
        LocalUser user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<LocalUser> updateUser(@RequestBody LocalUser user){
        LocalUser updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    



}
