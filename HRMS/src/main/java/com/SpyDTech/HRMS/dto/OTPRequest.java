package com.SpyDTech.HRMS.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OTPRequest {

    private String email;

    private String otp;

    private String newPassword;
}
