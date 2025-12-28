package com.admin.service;

import com.admin.model.Admin;
import com.admin.request.Login;

public interface AuthService {
    String registerAdmin(Admin admin) throws IllegalAccessException;
    String verifyRegister(Admin admin);
    String login(Login login);
}
