package com.example.kele.config.security;

import com.example.kele.entity.LoginUser;
import com.example.kele.repository.LoginUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author xukele
 * @since 2019/6/6 21:30
 * kele-practise & com.example.kele.config.security
 * <p>
 * 自定义登录验证逻辑;
 */
@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("===> 获取用户名:{}", username);

        LoginUser loginUser = userRepository.findByUsername(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new User(username, loginUser.getPassword(), Collections.emptyList());

    }
}
