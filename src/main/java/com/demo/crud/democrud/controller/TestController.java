package com.demo.crud.democrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/user")
  public String user() {
    return "you are a user";
  }

  @GetMapping("/admin")
  public String admin() {
    return "you are a admin";
  }
}
