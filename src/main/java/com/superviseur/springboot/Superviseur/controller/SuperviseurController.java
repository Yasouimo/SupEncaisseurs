package com.superviseur.springboot.Superviseur.controller;

import com.superviseur.springboot.Superviseur.model.Superviseur;
import com.superviseur.springboot.Superviseur.service.SuperviseurService;
import com.superviseur.springboot.Agence.service.AgenceService;
import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SuperviseurController {

    @Autowired
    private SuperviseurService superviseurService;

    @Autowired
    private AgenceService agenceService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(@RequestParam(value = "agenceId", required = false) Long agenceId, Model model) {
        List<Superviseur> superviseurs;
        if (agenceId == null || agenceId == 0) {
            superviseurs = superviseurService.getAllSuperviseurs();
        } else {
            model.addAttribute("agenceId", agenceId);
            superviseurs = superviseurService.getSuperviseursByAgenceId(agenceId);
        }
        model.addAttribute("listSuperviseur", superviseurs);
        model.addAttribute("listAgences", agenceService.getAllAgences());
        addWelcomeMessageToModel(model);
        return "index";
    }

    @GetMapping("/showNewSuperviseurForm")
    public String showNewSuperviseurForm(Model model) {
        Superviseur superviseur = new Superviseur();
        model.addAttribute("superviseur", superviseur);
        model.addAttribute("listAgences", agenceService.getAllAgences());
        addWelcomeMessageToModel(model);
        return "new_superviseur";
    }

    @PostMapping("/saveSuperviseur")
    public String saveSuperviseur(@ModelAttribute("superviseur") Superviseur superviseur) {
        superviseurService.saveSuperviseur(superviseur);
        return "redirect:/";
    }

    @GetMapping("/editSuperviseur/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Superviseur superviseur = superviseurService.getSuperviseurById(id);
        model.addAttribute("superviseur", superviseur);
        model.addAttribute("listAgences", agenceService.getAllAgences());
        addWelcomeMessageToModel(model);
        return "edit_superviseur";
    }

    @PostMapping("/updateSuperviseur")
    public String updateSuperviseur(@ModelAttribute("superviseur") Superviseur superviseur) {
        superviseurService.saveSuperviseur(superviseur);
        return "redirect:/";
    }

    @GetMapping("/deleteSuperviseur/{id}")
    public String deleteSuperviseur(@PathVariable("id") long id) {
        superviseurService.deleteSuperviseur(id);
        return "redirect:/";
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
