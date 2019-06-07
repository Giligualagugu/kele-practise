package com.example.kele.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xukele
 * @since 2019/6/6 21:12
 * kele-practise & com.example.kele.entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOGIN_USER_INFO")
public class LoginUser extends BaseEntity {

    private String username;

    private String password;

    private String telno;

    private String email;
    
}
