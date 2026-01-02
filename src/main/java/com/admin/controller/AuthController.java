package com.admin.controller;


import com.admin.model.Admin;
import com.admin.request.Login;
import com.admin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws IllegalAccessException {
//        log.info(admin.toString());
        return ResponseEntity.ok(authService.registerAdmin(admin));
    }

    @PostMapping("/registration-otp-verification")
    public ResponseEntity<String> registrationVerify(@RequestBody Admin admin){
        return ResponseEntity.ok(authService.verifyRegister(admin));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){

        return ResponseEntity.ok(authService.login(login));
    }
}
