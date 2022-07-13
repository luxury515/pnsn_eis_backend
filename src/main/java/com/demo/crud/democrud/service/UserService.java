package com.demo.crud.democrud.service;

import com.demo.crud.democrud.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  User save(User user);

  List<User> findAll();

  Optional<User> findById(Long userId);


}
