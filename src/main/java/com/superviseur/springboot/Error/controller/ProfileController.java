package com.superviseur.springboot.Error.controller;

import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String showProfilePage(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        addWelcomeMessageToModel(model);
        return "profile"; // Name of the profile HTML page
    }

    @GetMapping("/changePassword")
    public String showChangePasswordPage(Model model) {
        addWelcomeMessageToModel(model);
        return "change_password"; // Name of the HTML page
    }

    @PostMapping("/updatePassword")
    public String updatePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        User user = userService.getUserByEmail(username);

        if (user != null && passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Le mot de passe a été changé avec succès.");
            return "redirect:/"; // Redirect to the homepage or another page
        } else {
            redirectAttributes.addFlashAttribute("error", "Le mot de passe actuel est incorrect.");
            return "redirect:/changePassword"; // Return to the change password page with error message
        }
    }

    private void addWelcomeMessageToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            User user = userService.getUserByEmail(email);
            if (user != null) {
                // Concatenate role and user's last name in the welcome message
                String welcomeMessage = "Bienvenue " + user.getRole().getNom() + " " + user.getNom();
                model.addAttribute("welcomeMessage", welcomeMessage);
            }
        }
    }
}
