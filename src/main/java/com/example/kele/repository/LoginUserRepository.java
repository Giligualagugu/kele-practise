package com.example.kele.repository;

import com.example.kele.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xukele
 * @since 2019/6/6 21:20
 * kele-practise & com.example.kele.repository
 */
public interface LoginUserRepository extends JpaRepository<LoginUser,String> {
    LoginUser findByUsername(String username);
}
