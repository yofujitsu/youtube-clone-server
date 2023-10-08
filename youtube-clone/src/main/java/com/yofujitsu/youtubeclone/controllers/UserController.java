package com.yofujitsu.youtubeclone.controllers;

import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/register")
    public String register(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "register";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @PostMapping("/register")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("videos", user.getVideos());
        return "user-info";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "user-profile";
    }
}