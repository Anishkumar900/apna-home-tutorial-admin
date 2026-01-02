package com.admin.controller;


import com.admin.model.Admin;
import com.admin.service.ProfileAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/v1")
@Slf4j
public class AdminProfileController {

    private final ProfileAdminService profileAdminService;

    @GetMapping("/jwt-verify")
    public ResponseEntity<Admin> verifyAuthentication(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(profileAdminService.verifyAuthentication(token));

    }

    @GetMapping("/all-admin-details")
    public ResponseEntity<List<Admin>> allAdmin(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(profileAdminService.allAdmin());
    }

    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<String> deleteAdminById(@RequestHeader("Authorization") String token,@PathVariable Long id){
        return ResponseEntity.ok(profileAdminService.deleteAdminById(id));
    }



}
