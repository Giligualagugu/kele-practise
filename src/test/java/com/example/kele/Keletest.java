package com.example.kele;

import java.util.UUID;

public class Keletest {

	public static void main(String[] args) {

        String s = UUID.randomUUID().toString().replaceAll("-", "");

        System.out.println(s);
    }
}
