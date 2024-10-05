package com.ex1.demo.controller;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
  @Autowired
  private UserService userService;

  @GetMapping("health")
  public String m1() {
    log.info("check health");
    log.warn("check health");
    log.error("check health");
    log.debug("check health");
    return "ok";
  }

  @PostMapping("create-user")
  public ResponseEntity<User> save(@RequestBody User user) {
    try {
      User newUser = userService.save(user);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
