package com.demo.crud.democrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
  @Id
  @Column(name = "seq")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int seq;

  @Column(name = "username")
  private String username;

  @Column(name = "authority")
  private String authority;
}
