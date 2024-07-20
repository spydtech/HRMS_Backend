package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.SignUpRequest;
import com.SpyDTech.HRMS.dto.SignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();


}
