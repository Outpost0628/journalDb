package com.ex1.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
public class User {
  @Id
  private ObjectId id;
  /*
   * indexing means search will be idxscan and not collscan and unique means no
   * duplicate users allowed
   */
  @Indexed(unique = true)
  /*
   * will do null-checks and val. cannot be null and will throw NullPointerE if
   * field val. is null
   */
  @NonNull
  private String userName;
  @NonNull
  private String password;

  /*
   * to use JournalEntry collection in User collection we have to link
   * JournalEntry with User
   * to link coll. we give ref. of coll. using @DBRef we create ref. of
   * JournalEntry in User coll. it works as fk in sql
   */
  @DBRef
  private List<JournalEntry> journalEntries = new ArrayList<>();
  private List<String> roles;
}
