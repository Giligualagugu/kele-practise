package com.example.kele.controller;

import com.example.kele.entity.LoginUser;
import com.example.kele.repository.LoginUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xukele
 * @since 2019/6/6 21:19
 * kele-practise & com.example.kele.controller
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private LoginUserRepository loginUserRepository;


    @GetMapping("/test/adduser")
    public Object adduser() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String defaultpwd = "admin123";

        LoginUser kele = new LoginUser("kele", encoder.encode(defaultpwd), "17602160283", "1421295023@qq.com");

        LoginUser save = loginUserRepository.save(kele);

        return save;

    }

    @GetMapping("/getall")
    public Object getall() {
        List<LoginUser> all = loginUserRepository.findAll();

        return all;

    }
}
