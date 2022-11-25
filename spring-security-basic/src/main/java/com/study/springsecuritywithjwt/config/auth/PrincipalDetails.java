package com.study.springsecuritywithjwt.config.auth;

// 시큐리티가 로그인을 낚아채서 로그인을 진행시키도록 하였음.
// 로그인 진행이 완료되면 시큐리티 세션을 만들어준다. (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 유저 정보가 있어야 된다.
// User 오브젝트 타입 => UserDetails 타입 객체

import com.study.springsecuritywithjwt.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails 타입 (PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private final User user; // 컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> user.getRole());
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 1년 이상 로그인을 안한 계정 등등 역할 수행알 수 있음.
        return true;
    }
}
