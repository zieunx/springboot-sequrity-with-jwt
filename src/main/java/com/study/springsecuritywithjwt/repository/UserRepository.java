package com.study.springsecuritywithjwt.repository;

import com.study.springsecuritywithjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository 어노테이션은 붙히지 않아도 Ioc 된다. JpaRepository를 상속받기 때문
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
