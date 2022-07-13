package com.demo.crud.democrud.controller;

import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.service.UserService;
import com.demo.crud.democrud.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

//  private final UserService userService;
  private final UserServiceImp userServiceImp;

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userServiceImp.findAll());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserDetail(@PathVariable Long userId) {
    return ResponseEntity.ok(userServiceImp.findById(userId).orElse(null));
  }

  @PostMapping("/insert")
  public ResponseEntity<User> insertUser(@RequestBody User user) {
    log.info("user:{}", user);
    return ResponseEntity.ok(userServiceImp.save(user));
  }

  @DeleteMapping("/delete/{userId}")
  public String deleteUser(@PathVariable Long userId) {
    Optional<User> optional = userServiceImp.findById(userId);
    if (optional.isPresent()) {
      userServiceImp.deleteUserById(userId);
      return "is remove!";
    }
    return "Id [ " + userId + " ]is not found! Remove fail!!!";
  }
}
