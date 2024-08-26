package com.superviseur.springboot.Agence.controller;

import com.superviseur.springboot.Agence.model.Agence;
import com.superviseur.springboot.Agence.service.AgenceServiceImpl;
import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgenceController {

    @Autowired
    private AgenceServiceImpl agenceService;

    @Autowired
    private UserService userService;

    @GetMapping("/agences")
    public String viewAgencePage(Model model) {
        model.addAttribute("listAgences", agenceService.getAllAgences());
        addWelcomeMessageToModel(model);
        return "agences";
    }

    @GetMapping("/showNewAgenceForm")
    public String showNewAgenceForm(Model model) {
        model.addAttribute("agence", new Agence());
        addWelcomeMessageToModel(model);
        return "new_agence";
    }

    @PostMapping("/saveAgence")
    public String saveAgence(@ModelAttribute("agence") Agence agence) {
        agenceService.saveAgence(agence);
        return "redirect:/agences";
    }

    @GetMapping("/editAgence/{id}")
    public String showEditAgenceForm(@PathVariable("id") long id, Model model) {
        Agence agence = agenceService.getAgenceById(id);
        model.addAttribute("agence", agence);
        addWelcomeMessageToModel(model);
        return "edit_agence";
    }

    @PostMapping("/updateAgence")
    public String updateAgence(@ModelAttribute("agence") Agence agence) {
        agenceService.saveAgence(agence);
        return "redirect:/agences";
    }
    

    @GetMapping("/deleteAgence/{id}")
    public String deleteAgence(@PathVariable("id") long id) {
        agenceService.deleteAgence(id);
        return "redirect:/agences";
    }

    private void addWelcomeMessageToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            User user = userService.getUserByEmail(email);
            if (user != null) {
                String welcomeMessage = "Bienvenue " + user.getRole().getNom() + " " + user.getNom();
                model.addAttribute("welcomeMessage", welcomeMessage);
            }
        }
    }
}
