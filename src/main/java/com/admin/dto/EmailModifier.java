package com.admin.dto;

import org.springframework.stereotype.Service;

@Service
public class EmailModifier {
    public static String emailRewrite(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        return email.trim().toLowerCase();
    }

}
