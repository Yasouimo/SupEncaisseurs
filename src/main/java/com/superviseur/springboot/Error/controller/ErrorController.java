package com.superviseur.springboot.Error.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String accessDenied(Model model) {
        // Get the current authenticated user's role
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream()
                          .map(grantedAuthority -> grantedAuthority.getAuthority())
                          .findFirst()
                          .orElse("USER"); // Default to "USER" if no role is found

        model.addAttribute("message", "You do not have permission to access this page.");
        model.addAttribute("role", role); // Pass the role to the view

        return "403"; // This should match the name of your HTML template
    }
}
