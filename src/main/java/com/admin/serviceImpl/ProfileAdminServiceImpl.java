package com.admin.serviceImpl;

import com.admin.config.JWTService;
import com.admin.dto.EmailModifier;
import com.admin.model.Admin;
import com.admin.repository.AdminRepository;
import com.admin.service.ProfileAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileAdminServiceImpl implements ProfileAdminService {

    private final AdminRepository adminRepository;
    private final JWTService jwtService;


    @Override
    public Admin verifyAuthentication(String token) {
//        System.out.println("test");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization token");
        }
//        System.out.println("test1");
        String jwtToken = token.substring(7);
        String email = jwtService.extractUsername(jwtToken);
        email = EmailModifier.emailRewrite(email);
        String finalEmail = email;
//        log.info(jwtToken);
        return adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + finalEmail)
                );
    }

    @Override
    public List<Admin> allAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public String deleteAdminById(Long id) {
        try{
            adminRepository.deleteById(id);
            return "Delete successful";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
