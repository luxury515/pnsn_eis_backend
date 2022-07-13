package com.demo.crud.democrud.auth;

import com.demo.crud.democrud.entity.User;

public interface AuthService {
  User signUpUser(User user);

  String loginUser(User user);
}
