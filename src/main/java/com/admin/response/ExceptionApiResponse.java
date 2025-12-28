package com.admin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionApiResponse {
    private String message;
    private int status;
    private String error;
    private LocalDateTime createdTime;
}
