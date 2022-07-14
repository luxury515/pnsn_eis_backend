package com.demo.crud.democrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Salt {
  @Id @GeneratedValue private int id;

  public Salt(String salt) {
    this.salt = salt;
  }

  private String salt;
}
