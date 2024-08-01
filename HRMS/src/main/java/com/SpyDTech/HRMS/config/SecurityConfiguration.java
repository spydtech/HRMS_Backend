package com.SpyDTech.HRMS.config;

import com.SpyDTech.HRMS.exceptionHandling.CustomAccessDeniedHandler;
import com.SpyDTech.HRMS.exceptionHandling.CustomAuthenticationEntryPoint;
import com.SpyDTech.HRMS.entities.Role;
import com.SpyDTech.HRMS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    private final CustomAccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain...");
        http    .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request-> {logger.info("Configuring authorization requests...");
                        request.requestMatchers("/api/v1/auth/**")
                                 .permitAll()
                                .requestMatchers("/forgot-password" , "/reset-password","/facebookRecentPost/**","/projectDetail/**","/project/**","/Team/**").permitAll()

                                .requestMatchers("/api/v1/create/**").hasAnyAuthority(Role.SUPER_ADMIN.name(),Role.ADMIN.name(),Role.HR_ADMIN.name())
                                .requestMatchers("/api/v1/edit/**").hasAnyAuthority(Role.ADMIN.name(),Role.SUPER_ADMIN.name(),Role.HR_ADMIN.name())
                                .requestMatchers("/api/v1/delete").hasAuthority(Role.SUPER_ADMIN.name())
                                .requestMatchers("/api/v1/**").hasAnyAuthority(Role.SUPER_ADMIN.name(),Role.ADMIN.name(),Role.HR_ADMIN.name(),Role.EMPLOYEE.name())
                    .anyRequest().authenticated();})

                .sessionManagement(manager-> { logger.info("Configuring session management...");
                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);})

                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exceptionHandling->
                        exceptionHandling
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler));

        logger.info("Security filter chain configured successfully.");
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

        return config.getAuthenticationManager();
    }
}





