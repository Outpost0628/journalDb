package com.ex1.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
  /*
   * since @SpringBootApplication contains @Configuation we create beans in main
   * class and we give impleentation of PlatformTransactionManager which is
   * MongoTransactionManager which will handle tx
   * instance of MongoDatabaseFactory is SimpleMongoClientDatabaseFactory
   * we pass instance of MongoDatabaseFactory in MongoTransactionManager
   */
  @Bean
  PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
  }
}
