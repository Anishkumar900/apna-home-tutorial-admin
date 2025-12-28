package com.admin.controller;


import com.admin.model.Admin;
import com.admin.request.Login;
import com.admin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws IllegalAccessException {
        return ResponseEntity.ok(authService.registerAdmin(admin));
    }

    @PostMapping("/registration-verify")
    public ResponseEntity<String> registrationVerify(@RequestBody Admin admin){
        return ResponseEntity.ok(authService.verifyRegister(admin));
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        return ResponseEntity.ok(authService.login(login));
    }
}
