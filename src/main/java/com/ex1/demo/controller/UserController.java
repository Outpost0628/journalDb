package com.ex1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex1.demo.entity.User;
import com.ex1.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  // @GetMapping
  // public List<User> fetchAll() {
  // return userService.fetchAll();
  // }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody User user) {
    // holds loggedin cred.
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // get userName from loggedin cred.
    String userName = authentication.getName();
    User old = userService.findByUserName(userName);
    if (old != null) {
      old.setUserName(user.getUserName());
      old.setPassword(user.getPassword());
      userService.save(old);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping
  public ResponseEntity<?> delete() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    userService.deleteByUserName(authentication.getName());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
