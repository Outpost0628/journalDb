package com.ex1.demo.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/*will tell spring, class is mapped to mongodb collection and new instance of this class will be equal to single document */
@Document(collection = "journal_entries")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JournalEntry {
  // unique field and we use ObjectId as dt which is a dt of _id field in mongodb
  @Id
  private ObjectId id;
  @NonNull
  private String title;
  private String content;
  private LocalDateTime date;
}
