package com.ex1.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex1.demo.entity.User;
import com.ex1.demo.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private UserService userService;

  @GetMapping("/all-users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> all = userService.fetchAll();
    if (all != null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/create-admin-user")
  public void saveAdmin(@RequestBody User user) {
    userService.saveAdmin(user);
  }
}
