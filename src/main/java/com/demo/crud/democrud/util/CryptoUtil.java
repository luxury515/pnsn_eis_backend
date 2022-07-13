package com.demo.crud.democrud.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

public class CryptoUtil {
  public static String encodePassword(String salt, String password) {
    return BCrypt.hashpw(password, salt);
  }

  public static String genSalt() {
    return BCrypt.gensalt();
  }
}
