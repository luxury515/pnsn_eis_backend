package com.demo.crud.democrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(
//        name = "USER_SEQ_GENERATOR",
//        sequenceName = "USER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1,
//        allocationSize = 1)
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "USER_SEQ_GENERATOR")
    @GeneratedValue(generator = "USER_GENERATOR")
    @GenericGenerator(name = "USER_GENERATOR",strategy = "uuid")
    private String id;
    private String name;
    private String url;

}
