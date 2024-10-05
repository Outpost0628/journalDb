package com.ex1.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ex1.demo.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
  // we update user by username so create custom method
  User findByUserName(String user);

  void deleteByUserName(String user);
}
