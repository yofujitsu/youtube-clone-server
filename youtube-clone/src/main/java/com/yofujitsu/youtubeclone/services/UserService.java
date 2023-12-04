package com.yofujitsu.youtubeclone.services;

import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.dao.entities.enums.Role;
import com.yofujitsu.youtubeclone.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email)!=null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        log.info("Saving new user with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public void followUser(User currentUser, User userToFollow) {
        currentUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);
        userToFollow.setFollowersCount(userToFollow.getFollowersCount() + 1);
        userRepository.save(currentUser);
        userRepository.save(userToFollow);
    }

    public void unfollowUser(User currentUser, User userToUnfollow) {
        currentUser.getFollowing().remove(userToUnfollow);
        userToUnfollow.getFollowers().remove(currentUser);
        userToUnfollow.setFollowersCount(userToUnfollow.getFollowersCount() - 1);
        userRepository.save(currentUser);
        userRepository.save(userToUnfollow);
    }

    public boolean isUserSubscribed(Long currentUserId, Long userIdToCheck) {
        User currentUser = userRepository.findById(currentUserId).orElse(null);
        User userToCheck = userRepository.findById(userIdToCheck).orElse(null);
        return currentUser.getFollowing().contains(userToCheck);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null) {
            if(user.isActive()) {
                user.setActive(false);
                log.info("Banned user with id: {}; Email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unbanned user with id: {}; Email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if(roles.contains(key)) user.getRoles().add(Role.valueOf(key));
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
