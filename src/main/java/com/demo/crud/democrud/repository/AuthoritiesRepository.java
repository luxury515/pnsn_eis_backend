package com.demo.crud.democrud.repository;

import com.demo.crud.democrud.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authority,Integer> {
    List<Authority> findByUsername(String username);
}
