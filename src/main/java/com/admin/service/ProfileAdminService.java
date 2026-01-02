package com.admin.service;

import com.admin.model.Admin;

import java.util.List;

public interface ProfileAdminService {
    Admin verifyAuthentication(String token);
    List<Admin> allAdmin();
    String deleteAdminById(Long id);
}
