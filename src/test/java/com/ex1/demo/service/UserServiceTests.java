package com.ex1.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ex1.demo.entity.User;
import com.ex1.demo.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {
  @Autowired
  private UserRepository userRepository;

  // @BeforeEach
  // void a1() {
  // }

  // @BeforeAll
  // void a2() {
  // }

  @ParameterizedTest
  // @ArgumentsSource(UserArgumentProvider.class)
  @ValueSource(strings = { "ram", "user2" })
  @Disabled
  public void findByUserName(String user) {
    assertEquals(4, 2 + 2);
    User user1 = userRepository.findByUserName(user);
    assertNotNull(user1.getUserName(), "failed for user: " + user);
    assertTrue(!user1.getJournalEntries().isEmpty());
    assertTrue(1 < 2);
  }

  @ParameterizedTest
  @CsvSource({ "1,1,2", "2,2,4", "3,3,6" })
  @Disabled
  public void test1(int a, int b, int c) {
    assertEquals(c, a + b);
  }
}
