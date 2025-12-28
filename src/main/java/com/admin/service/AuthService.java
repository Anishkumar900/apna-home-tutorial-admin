package com.admin.service;

import com.admin.model.Admin;

public interface AuthService {
    String registerAdmin(Admin admin) throws IllegalAccessException;
    String verifyRegister(Admin admin);
    String login();
}
