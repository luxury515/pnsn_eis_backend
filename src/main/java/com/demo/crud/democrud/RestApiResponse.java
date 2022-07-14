package com.demo.crud.democrud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResponse<T> {
  private int code;
  private String message;
  private T data;
}
