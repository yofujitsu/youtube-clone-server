package com.yofujitsu.youtubeclone.controllers;

import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.dao.repositories.UserRepository;
import com.yofujitsu.youtubeclone.services.ContentUnitService;
import com.yofujitsu.youtubeclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private final UserService userService;
    private final ContentUnitService contentUnitService;
    private final UserRepository userRepository;

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
    public String profile(@RequestParam Long id, Principal principal,
                          Model model) {
        User currentUser = userService.getUserByPrincipal(principal);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("contentUnits", contentUnitService.listContentUnits(user.getId()));
        model.addAttribute("currentUserContentUnits", contentUnitService.listContentUnits(currentUser.getId()));
        boolean isUserSubscribed = userService.isUserSubscribed(currentUser.getId(), user.getId());
        // Передача isUserSubscribed в модель для использования в представлении
        model.addAttribute("isUserSubscribed", isUserSubscribed);
        return "user-profile";
    }

    @PostMapping("/profile/subscribe")
    public String subscribeToUser(@RequestParam("userIdToFollow") Long userIdToFollow, Principal principal, RedirectAttributes redirectAttributes) {
        User currentUser = userService.getUserByPrincipal(principal);
        User userToFollow = userRepository.findById(userIdToFollow).orElse(null);
        log.info("current user: {}; userToFollow: {}; userToFollowFromRequest: {}", currentUser.getId(), userToFollow.getId(), userIdToFollow);
        if (!currentUser.equals(userToFollow) && !userService.isUserSubscribed(currentUser.getId(), userIdToFollow)) {
            userService.followUser(currentUser, userToFollow);
            System.out.println("ПОДПИСАЛСЯ!!!!!!!!!!!!!!!!");
        }
        else if(!currentUser.equals(userToFollow) && userService.isUserSubscribed(currentUser.getId(), userIdToFollow)) {
            userService.unfollowUser(currentUser, userToFollow);
            System.out.println("ОТПИСАЛСЯ!!!!!!!!!!!!!!!!");
        }
        redirectAttributes.addAttribute("id", userToFollow.getId());
        return "redirect:/profile";
    }
}