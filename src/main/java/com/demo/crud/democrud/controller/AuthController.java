package com.demo.crud.democrud.controller;

import com.demo.crud.democrud.RestApiResponse;
import com.demo.crud.democrud.auth.AuthServiceImp;
import com.demo.crud.democrud.entity.User;
import com.demo.crud.democrud.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthServiceImp authServiceImp;

  @PostMapping("/signUp")
  public ResponseEntity<RestApiResponse<User>> signUpUser(@RequestBody User user) {
    User result = authServiceImp.signUpUser(user);
    RestApiResponse<User> restApiResponse = new RestApiResponse<User>();
    restApiResponse.setCode(200);
    restApiResponse.setMessage("가입 성공");
    restApiResponse.setData(result);
    return ResponseEntity.ok(restApiResponse);
  }

  @PostMapping("/login")
  public ResponseEntity<RestApiResponse<String>> login(@RequestBody User user) {
    String result = authServiceImp.loginUser(user);
    RestApiResponse<String> restApiResponse = new RestApiResponse<String>();
    log.info("로그인 한 사용자:{}", user);
    if ("".equals(result)) {
      restApiResponse.setCode(401);
      restApiResponse.setMessage("존재하지않는 사용자거나 패스워드가 틀립니다.");
      return ResponseEntity.ok(restApiResponse);
    }
    restApiResponse.setCode(200);
    restApiResponse.setMessage("로그인 성공.");
    restApiResponse.setData(result);
    return ResponseEntity.ok(restApiResponse);
  }
}
