package com.demo.crud.democrud.controller;

import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetail(@PathVariable String userId){
        return ResponseEntity.ok(userService.findById(userId).orElse(null));
    }
    @PostMapping("/insert")
    public ResponseEntity insertUser(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId){
       Optional<User> optional =userService.findById(userId);
        if(optional.isPresent()){
            userService.deleteUser(optional.get());
            return "is remove!";
        }
        return "id [ "+userId+" ]is remove fail!!";
    }
}
