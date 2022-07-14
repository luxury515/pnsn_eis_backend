package com.demo.crud.democrud.auth;

import com.demo.crud.democrud.entity.Salt;
import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.repository.UserRepository;
import com.demo.crud.democrud.util.CryptoUtil;
import com.demo.crud.democrud.util.JwtTokenProvider;
import enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImp implements AuthService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public User signUpUser(User user) {
    String password = user.getPassword();
    String salt = CryptoUtil.genSalt();
    user.setSalt(new Salt(salt));
    user.setPassword(CryptoUtil.encodePassword(salt, password));
    if (user.getRoles().isEmpty()) {
      user.setRoles(Collections.singletonList(UserRole.ROLE_USER.name()));
    } else {
      user.setRoles(user.getRoles());
    }
    return userRepository.save(user);
  }

  @Override
  public String loginUser(User user) {
    Optional<User> o = userRepository.findByUsername(user.getUsername());
    log.info("rolse:{}", o.get().getRoles());
    if (o.isPresent()) {
      if (o.get()
          .getPassword()
          .equals(CryptoUtil.encodePassword(o.get().getSalt().getSalt(), user.getPassword()))) {
        log.info("패스워드가 동일합니다.");
      } else {
        log.info("패스워드가 틀립니다.");
        return "";
      }
    } else {
      log.info("사용자 존재하지 않습니다.");
      return "";
    }
    return jwtTokenProvider.createToken(o.get().getUsername(), o.get().getRoles());
  }
}
