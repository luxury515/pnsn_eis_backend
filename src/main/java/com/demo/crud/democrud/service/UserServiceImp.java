package com.demo.crud.democrud.service;

import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
  private final UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(String userId) {
    return userRepository.findById(userId);
  }



  @Override
  public void deleteUserById(String userId) {
    userRepository.deleteById(userId);
  }
}
