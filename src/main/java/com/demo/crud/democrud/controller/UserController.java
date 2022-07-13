package com.demo.crud.democrud.controller;

import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserDetail(@PathVariable String userId) {
    return ResponseEntity.ok(userService.findById(userId).orElse(null));
  }

  @PostMapping("/insert")
  public ResponseEntity<User> insertUser(@RequestBody User user) {
    log.info("user:{}", user);
    return ResponseEntity.ok(userService.save(user));
  }

  @DeleteMapping("/delete/{userId}")
  public String deleteUser(@PathVariable String userId) {
    userService.findById(userId).orElseThrow(()->new UsernameNotFoundException("사용자 없음!"));
    userService.deleteUserById(userId);
    return "id [ " + userId + " ]is remove fail!!";
  }
}
