package com.study.springsecuritywithjwt.controller;

import com.study.springsecuritywithjwt.model.User;
import com.study.springsecuritywithjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("join")
    public String join(User user) {
        System.out.println("user = " + user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        // 바로 저장하면 시큐리티로 로그인 할 수 없음. 비밀번호가 암호화 안되어있기 때문
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("info")
    public @ResponseBody String info() {
        return "개인정보";
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("data")
    public @ResponseBody String data() {
        return "data";
    }
}
