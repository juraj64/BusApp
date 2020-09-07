package com.jurajlazovy.bus.serviceimpl;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validatePassword(String password) {
        String pattern = "123";

        return password.matches(pattern);
    }
}