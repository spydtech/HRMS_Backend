package com.SpyDTech.HRMS.service.impl;


import com.SpyDTech.HRMS.entities.User;
import com.SpyDTech.HRMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl {

    private final UserRepository userRepository;

    private final EmailServiceImpl emailService;

    private final PasswordEncoder passwordEncoder;

    private static final long EXPIRE_TOKEN=30;

    public String forgotPass(String email){
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user=userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user=userRepository.save(user);
        String resetLink = "http://localhost:8080/reset-password?token=" + user.getToken();
        emailService.sendEmail(user.getEmail(), "Reset Password", "Click the link to reset your password: " + resetLink);

        return "Reset link sent to your email.";


    }
    public String resetPass(String token, String password){
        Optional<User> userOptional= Optional.ofNullable(userRepository.findByToken(token));

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        User user = userOptional.get();

        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >=EXPIRE_TOKEN;
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
