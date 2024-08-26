package com.superviseur.springboot.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.superviseur.springboot.Role.service.RoleService;
import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder

    @GetMapping("/users")
    public String viewUserList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        model.addAttribute("listUsers", userService.getAllUsersExcluding(email));
        model.addAttribute("message", "Here is the list of all users.");
        addWelcomeMessageToModel(model);
        return "show_user";
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("excludeAdminRole", true); // Restrict admin role option
        addWelcomeMessageToModel(model);
        return "new_user";
    }

    @GetMapping("/editUser/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("excludeAdminRole", true); // Restrict admin role option
        addWelcomeMessageToModel(model);

        // Exclude admin role if user is an admin
        if (user.getRole().getNom().equalsIgnoreCase("admin")) {
            model.addAttribute("excludeAdminRole", true);
        }

        return "edit_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("message", "Cet email existe déjà !");
            model.addAttribute("listRoles", roleService.getAllRoles());
            return "new_user";
        }

        // Encode the password before saving
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        // Ensure the password is not updated unless it is explicitly set
        User existingUser = userService.getUserById(user.getId());
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
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
