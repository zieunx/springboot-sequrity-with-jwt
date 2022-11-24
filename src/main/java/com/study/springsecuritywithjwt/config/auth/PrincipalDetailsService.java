package com.study.springsecuritywithjwt.config.auth;

import com.study.springsecuritywithjwt.model.User;
import com.study.springsecuritywithjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 .loginProcessingUrl("login") 해줬음
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 객체의 loadUserByUsername 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 Session(내부에 Authentication(내부에 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        User user = userRepository.findByUsername(username);

        if (username == null) {
            return null;
        }
        return new PrincipalDetails(user);
    }
}
