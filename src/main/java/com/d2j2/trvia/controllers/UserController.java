package com.d2j2.trvia.controllers;

import com.d2j2.trvia.entities.AppUser;
import com.d2j2.trvia.repositories.RoleRepository;
import com.d2j2.trvia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/signup")
    public String signUpNewUsers(Model model){
        model.addAttribute("newUser", new AppUser());
        return "signup";
    }
    @PostMapping("/saveuser")
    public String saveNewUsers(@Valid @ModelAttribute("newUser") AppUser appUser, BindingResult result){
        if(result.hasErrors()){
            return "signup";
        }
        appUser.addRoles(roleRepository.findByRoleName("USER"));
        userRepository.save(appUser);
        return "redirect:login";
    }
    @GetMapping("login")
    public String loginUser(){
        return "login";
    }
}
