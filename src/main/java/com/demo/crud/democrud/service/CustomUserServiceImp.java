package com.demo.crud.democrud.service;

import com.demo.crud.democrud.entity.Authority;
import com.demo.crud.democrud.repository.AuthoritiesRepository;
import com.demo.crud.democrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserServiceImp implements UserDetailsService {

  private final UserRepository userRepository;

  private final AuthoritiesRepository authoritiesRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

//    User user = userRepository.findByUsername(username);
//    if ("null".equals(user)) {
//      throw new UsernameNotFoundException("not found user:" + username);
//    }
//
//    CustomUser customUser = new CustomUser();
//    customUser.setUsername(user.getUsername());
//    customUser.setPassword(user.getPassword());
//    customUser.setAuthorities(getAuthorities(username));
//    customUser.setEnabled(true);
//    customUser.setAccountNonExpired(true);
//    customUser.setAccountNonLocked(true);
//    customUser.setCredentialsNonExpired(true);
//    return customUser;
  }

  public Collection<GrantedAuthority> getAuthorities(String username) {
    List<Authority> authList = authoritiesRepository.findByUsername(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Authority authority : authList) {
      authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
    }
    return authorities;
  }
}
