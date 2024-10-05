package com.ex1.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.ex1.demo.entity.User;
import com.ex1.demo.repository.UserRepository;

/* @SpringBootTest: will start spring appl. context and Component will be created and di will work, if we dont put it will give NullPointerE */
@SpringBootTest
public class UserDetailsServiceImplTests {
  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  // @MockBean: will add mock in spring appl. context
  @MockBean
  private UserRepository userRepository;

  // @Test
  // @Disabled
  // void testLoadUserByUsername() {
  // Mockito.when(userRepository.findByUserName(ArgumentMatchers.anyString()))
  // .thenReturn(User.builder().userName("user2").password("random").roles(new
  // ArrayList<>()).build());
  // UserDetails user = userDetailsServiceImpl.loadUserByUsername("user2");
  // assertNotNull(user);
  // }
}
