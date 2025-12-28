package com.admin.model;

import com.admin.dom.VerifyAdmin;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    private Long id;
    private String email;
    private String password;
    private String otp;
    private LocalDateTime expireOtpTime;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private VerifyAdmin verifyAdmin;
}
