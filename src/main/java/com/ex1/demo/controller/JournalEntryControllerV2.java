package com.ex1.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex1.demo.entity.JournalEntry;
import com.ex1.demo.entity.User;
import com.ex1.demo.service.JournalEntryService;
import com.ex1.demo.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserService userService;
  // we cant have same path with same mapping sf will give error

  @GetMapping
  public ResponseEntity<List<JournalEntry>> fetchAllJournalEntriesOfUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user1 = userService.findByUserName(userName);
    List<JournalEntry> all = user1.getJournalEntries();
    if (all != null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /*
   * @RequestBody tells sf hey take data from request and turn it into java obj.
   * that i can use in my java code. whatever data client put as req. in postman
   * it will be saved in je1 and will be converted in pojo
   */
  @PostMapping
  public ResponseEntity<?> save(@RequestBody JournalEntry newEntry) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
      journalEntryService.save(newEntry, userName);
      return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /*
   * we have @PathVariable and @RequestParam we give rp after ?
   * and pv after / inside {} in endpoint
   */
  @GetMapping("id/{id}")
  public ResponseEntity<JournalEntry> fetchById(@PathVariable ObjectId id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUserName(userName);
    List<JournalEntry> collect1 = user.getJournalEntries().stream().filter(s -> s.getId().equals(id))
        .collect(Collectors.toList());
    if (!collect1.isEmpty()) {
      Optional<JournalEntry> o1 = journalEntryService.findById(id);
      if (o1.isPresent()) {
        return new ResponseEntity<>(o1.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("id/{id}")
  public ResponseEntity<?> deleteById(@PathVariable ObjectId id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    boolean removed1 = journalEntryService.deleteById(id, userName);
    if (removed1) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("id/{id}")
  public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUserName(userName);
    List<JournalEntry> collect1 = user.getJournalEntries().stream().filter(s -> s.getId().equals(id))
        .collect(Collectors.toList());
    if (!collect1.isEmpty()) {
      Optional<JournalEntry> o1 = journalEntryService.findById(id);
      if (o1.isPresent()) {
        JournalEntry old = o1.get();
        old.setTitle(
            updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle()
                : old.getTitle());
        old.setContent(
            updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent()
                : old.getContent());
        journalEntryService.save(old);
        return new ResponseEntity<>(old, HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
