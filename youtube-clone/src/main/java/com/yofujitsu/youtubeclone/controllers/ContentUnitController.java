package com.yofujitsu.youtubeclone.controllers;

import com.yofujitsu.youtubeclone.dao.entities.ContentUnit;
import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.dao.entities.Video;
import com.yofujitsu.youtubeclone.dao.repositories.ContentUnitRepository;
import com.yofujitsu.youtubeclone.dao.repositories.VideoRepository;
import com.yofujitsu.youtubeclone.services.ContentUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ContentUnitController {
    private final ContentUnitService contentUnitService;
    private final ContentUnitRepository contentUnitRepository;
    private final VideoRepository videoRepository;


    @GetMapping("/youtube")
    public String contentUnits(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
        model.addAttribute("contentUnits", contentUnitService.listContentUnits(title));
        model.addAttribute("user", contentUnitService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "catalog";
    }


    @GetMapping("/contentUnit")
    public String contentUnitInfo(@RequestParam Long id, Model model, Principal principal) {
        ContentUnit contentUnit = contentUnitService.getContentUnitById(id);
        model.addAttribute("user", contentUnitService.getUserByPrincipal(principal));
        model.addAttribute("contentUnit", contentUnit);
        model.addAttribute("video", contentUnit.getVideo());
        contentUnitService.watchContentUnit(id);
        return "videoplayer";
    }

    @PostMapping("/contentUnit/create")
    public String createContentUnit(ContentUnit contentUnit, Principal principal, String url)
            throws IOException {
        contentUnitService.saveContentUnit(principal, contentUnit, url);
        return "redirect:/profile";
    }

    @PostMapping("/contentUnit/delete")
    public String deleteContentUnit(@RequestParam Long id) {
        contentUnitService.deleteContentUnit(id);
        return "redirect:/profile";
    }

    @GetMapping("/my/contentUnits")
    public String userContentUnits(Principal principal, Model model) {
        User user = contentUnitService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("contentUnits", user.getVideos());
        return "create-video";
    }

    @PostMapping("/contentUnit")
    public String likeContentUnit(@PathVariable Long id) {
        contentUnitService.watchContentUnit(id);
        return "redirect:/contentUnit/{id}";
    }

    @GetMapping("/contentUnit/edit")
    public String updateContentUnits(@RequestParam Long id, Principal principal, Model model) {
        User user = contentUnitService.getUserByPrincipal(principal);
        ContentUnit contentUnit = contentUnitService.getContentUnitById(id);
        model.addAttribute("user", user);
        model.addAttribute("contentUnit", contentUnit);
        model.addAttribute("redirect", "user-profile");
        return "edit-video";
    }

    @PostMapping("/contentUnit/edit")
    public String updateContentUnit(@RequestParam Long id, Principal principal, @ModelAttribute ContentUnit updatedContentUnit) {
        ContentUnit contentUnit = contentUnitService.getContentUnitById(id);
        contentUnit.setThumbnailUrl(updatedContentUnit.getThumbnailUrl());
        contentUnit.setTitle(updatedContentUnit.getTitle());
        contentUnit.setDescription(updatedContentUnit.getDescription());
        contentUnitRepository.save(contentUnit);
        return "redirect:/profile";
    }
}