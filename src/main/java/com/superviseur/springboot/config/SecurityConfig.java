package com.superviseur.springboot.config;

import com.superviseur.springboot.constants.TbConstants;
import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/login", "/logout", "/updatePassword", "/403"))
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/registration/**", "/login/**", "/images/**", "/css/**", "/js/**", "/403").permitAll()
            .requestMatchers("/users/**").hasRole(TbConstants.ROLE_ADMIN)
            .requestMatchers("/agences/**").hasAnyRole(TbConstants.ROLE_USER, TbConstants.ROLE_ADMIN)
            .requestMatchers("/showNewUserForm/**", "/editUser/**", "/saveUser/**", "/updateUser/**", "/deleteUser/**").hasRole(TbConstants.ROLE_ADMIN)
            .requestMatchers("/showNewAgenceForm/**", "/editAgence/**", "/saveAgence/**", "/updateAgence/**", "/deleteAgence/**").hasRole(TbConstants.ROLE_ADMIN)
            .requestMatchers("/showNewSuperviseurForm/**", "/editSuperviseur/**", "/saveSuperviseur/**", "/updateSuperviseur/**", "/deleteSuperviseur/**").hasRole(TbConstants.ROLE_MANAGER)
            .requestMatchers("/changePassword", "/updatePassword/**", "/profile/**").hasAnyRole(TbConstants.ROLE_USER, TbConstants.ROLE_MANAGER)
            .requestMatchers("/").hasAnyRole(TbConstants.ROLE_MANAGER, TbConstants.ROLE_USER)
            .anyRequest().denyAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll()
                .failureHandler((request, response, exception) -> response.sendRedirect("/login?error=true"))
            )
            .logout(logout -> logout
                .logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/login?logout=true"))
            )
            .exceptionHandling(withDefaults -> 
                withDefaults.accessDeniedHandler(accessDeniedHandler())
            );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String email = userDetails.getUsername();
                User user = userService.getUserByEmail(email);

                if (user != null) {
                    String roleName = user.getRole().getNom().toUpperCase();
                    if (TbConstants.ROLE_ADMIN.equals(roleName)) {
                        response.sendRedirect("/users");
                    } else if (TbConstants.ROLE_MANAGER.equals(roleName)) {
                        response.sendRedirect("/"); // Redirect for manager
                    } else {
                        response.sendRedirect("/");
                    }
                } else {
                    response.sendRedirect("/login?error=true");
                }
            }
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
