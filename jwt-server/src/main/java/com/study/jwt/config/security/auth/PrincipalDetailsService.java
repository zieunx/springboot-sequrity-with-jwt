package com.study.jwt.config.security.auth;

import com.study.jwt.domain.user.User;
import com.study.jwt.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("[PrincipalDetailsService] username: " + username);
        User user = userRepository.findByUserId(username);

        if (username == null) {
            return null;
        }
        return new PrincipalDetails(user);
    }
}
