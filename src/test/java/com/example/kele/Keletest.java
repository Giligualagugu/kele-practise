package com.example.kele;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Keletest {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        String kele123 = passwordEncoder.encode("kele123");

        System.out.println(kele123);

    }
}
