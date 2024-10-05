package com.ex1.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ex1.demo.entity.JournalEntry;
import com.ex1.demo.entity.User;
import com.ex1.demo.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
  /*
   * spring created the instance and we inject it using @Autowired. at runtime
   * spring will add JournalEntryRepository interface impl.
   */
  @Autowired
  private JournalEntryRepository journalEntryRepository;
  @Autowired
  private UserService userService;

  /*
   * it will tell spring if any opr. failed every successful opr. will be rollback
   */
  @Transactional
  public void save(JournalEntry journalEntry, String userName) {
    try {
      User user1 = userService.findByUserName(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry entry1 = journalEntryRepository.save(journalEntry);
      // add entry1 in journalEntries field
      user1.getJournalEntries().add(entry1);

      /*
       * checking tx opr. we have userName as NonNull in User entity and here we set
       * null so will give NullPointerE
       */
      // user1.setUserName(null);

      userService.saveUser(user1);
    } catch (Exception e) {

      throw new RuntimeException("tx failed");
    }
  }

  public JournalEntry save(JournalEntry journalEntry) {
    return journalEntryRepository.save(journalEntry);
  }

  public List<JournalEntry> fetchAll() {
    return journalEntryRepository.findAll();
  }

  public Optional<JournalEntry> findById(ObjectId id) {
    return journalEntryRepository.findById(id);
  }

  @Transactional
  public boolean deleteById(ObjectId id, String userName) {
    try {
      User user1 = userService.findByUserName(userName);

      /*
       * remove journalEntries ref. from User coll. when journalEntry deleted from
       * JournalEntry coll. and save the doc.
       */
      boolean removed1 = user1.getJournalEntries().removeIf(s -> s.getId().equals(id));
      userService.saveUser(user1);

      // delete journalEntry by id
      journalEntryRepository.deleteById(id);
      return removed1;
    } catch (Exception e) {
      System.out.println(e.toString());
      throw new RuntimeException("erro occured while deleting entry: " + e);
    }
  }
}
