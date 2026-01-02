package com.admin.serviceImpl;

import com.admin.config.JWTService;
import com.admin.dom.VerifyAdmin;
import com.admin.dto.EmailModifier;
import com.admin.dto.OTPGenerate;
import com.admin.emailService.EmailSenderOtp;
import com.admin.exception.AdminSaveException;
import com.admin.exception.UserAlreadyExistException;
import com.admin.exception.UserNotFoundException;
import com.admin.model.Admin;
import com.admin.repository.AdminRepository;
import com.admin.request.Login;
import com.admin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTService jwtService;

    private final EmailSenderOtp emailSenderOtp;

    @Override
    public String registerAdmin(Admin admin) throws IllegalAccessException {
        String email = EmailModifier.emailRewrite(admin.getEmail());
        admin.setEmail(email);

        Optional<Admin> existing = adminRepository.findByEmail(email);
        String otp = OTPGenerate.otpGenerate(4);

        if (existing.isPresent()) {
            if (existing.get().getVerifyAdmin() != VerifyAdmin.PENDING) {
                throw new UserAlreadyExistException("User already exists!");
            }
            adminRepository.delete(existing.get());
        }

        emailSenderOtp.sendOtpEmail("apnahometutorial@gmail.com","Admin",otp);

        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setVerifyAdmin(VerifyAdmin.PENDING);
        admin.setOtp(otp);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setExpireOtpTime(LocalDateTime.now().plusMinutes(15));
        try {
            adminRepository.save(admin);
            return "Please verify!";
        } catch (Exception e) {
            throw new AdminSaveException("Failed to save admin");
        }

    }

    @Override
    public String verifyRegister(Admin admin) {
        String email = EmailModifier.emailRewrite(admin.getEmail());
        admin.setEmail(email);

        Admin existing = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));


        if(existing.getVerifyAdmin() != VerifyAdmin.PENDING){
            throw new UserAlreadyExistException("User already exist!");
        }

        if(!Objects.equals(existing.getOtp(),admin.getOtp())){
            throw new BadCredentialsException("Invalid OTP!");
        }
        if(existing.getExpireOtpTime().isBefore(LocalDateTime.now())){
            throw new BadCredentialsException("OTP expired!");
        }

        existing.setVerifyAdmin(VerifyAdmin.VERIFIED);
        existing.setOtp(null);
        existing.setExpireOtpTime(null);
        existing.setCreatedAt(LocalDateTime.now());

        try {
            adminRepository.save(existing);
            return "Account created successfully!";
        } catch (Exception e) {
            throw new AdminSaveException("Failed to verify admin");
        }
    }

    @Override
    public String login(Login login) {
        String email = EmailModifier.emailRewrite(login.getEmail());
        login.setEmail(EmailModifier.emailRewrite(login.getEmail()));

        Admin admin=adminRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found!"));

        if(admin.getVerifyAdmin() != VerifyAdmin.VERIFIED){
            throw new UserNotFoundException("User not found!");
        }
        if (!bCryptPasswordEncoder.matches(login.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("Invalid email or password!");
        }
        return jwtService.generateJWTToken(email);
    }
}
