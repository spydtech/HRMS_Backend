package com.SpyDTech.HRMS.service.impl;


import com.SpyDTech.HRMS.dto.OTPRequest;
import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl {


    private final UserRepository userRepository;

    private final EmailServiceImpl emailService;

    private final PasswordEncoder passwordEncoder;

    private static final long EXPIRE_OTP_DURATION = 5; // OTP expiry duration in minutes
    private static final int OTP_LENGTH = 6;

    public String forgotPass(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        String otp = generateOTP();
        user.setOtp(otp);
        user.setOtpCreationDate(LocalDateTime.now());

        user = userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Reset Password OTP", "Your OTP for password reset is: " + otp);

        return "OTP sent to your email.";
    }

    public String resetPass(OTPRequest otpRequest) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(otpRequest.getEmail()));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();

        if (!user.getOtp().equals(otpRequest.getOtp())) {
            return "Invalid OTP.";
        }

        if (isOtpExpired(user.getOtpCreationDate())) {
            return "OTP expired.";
        }

        user.setPassword(passwordEncoder.encode(otpRequest.getNewPassword()));
        user.setOtp(null);
        user.setOtpCreationDate(null);

        userRepository.save(user);

        return "Your password has been successfully updated.";
    }

    private String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

    private boolean isOtpExpired(final LocalDateTime otpCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(otpCreationDate, now);

        return diff.toMinutes() >= EXPIRE_OTP_DURATION;
    }


    public String updatePassword(String username, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (!userOptional.isPresent()) {
            return "User not found.";
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return "Your password has been successfully updated.";
    }
}
