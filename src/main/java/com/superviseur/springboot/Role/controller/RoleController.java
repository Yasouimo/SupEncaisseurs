package com.superviseur.springboot.Role.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.superviseur.springboot.Role.service.RoleService;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public String viewRolesPage(Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "roles"; // Cette page peut être laissée vide si vous ne souhaitez pas avoir une page dédiée aux rôles
    }

}
