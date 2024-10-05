package com.ex1.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ex1.demo.entity.User;
import com.ex1.demo.repository.UserRepository;

@Component
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  // save new user
  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("user"));
    return userRepository.save(user);
  }

  // save new admin-user
  public User saveAdmin(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("user", "admin"));
    return userRepository.save(user);
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public List<User> fetchAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(ObjectId id) {
    return userRepository.findById(id);
  }

  public void deleteById(ObjectId id) {
    userRepository.deleteById(id);
  }

  public User findByUserName(String user) {
    return userRepository.findByUserName(user);
  }

  public void deleteByUserName(String user) {
    userRepository.deleteByUserName(user);
  }
}
