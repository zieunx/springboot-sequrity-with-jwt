package com.study.jwt.domain.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "member")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, length = 50)
    private String userId;

    @Column
    private String password;

    protected User() {}

    @Builder
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
