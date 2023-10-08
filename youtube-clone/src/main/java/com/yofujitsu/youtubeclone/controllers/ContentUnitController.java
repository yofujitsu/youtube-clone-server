package com.yofujitsu.youtubeclone.controllers;

import com.yofujitsu.youtubeclone.dao.entities.ContentUnit;
import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.services.ContentUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ContentUnitController {
    private final ContentUnitService contentUnitService;


    @GetMapping("/youtube")
    public String contentUnits(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
        model.addAttribute("contentUnits", contentUnitService.listContentUnits(title));
        model.addAttribute("user", contentUnitService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "catalog";
    }

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        model.addAttribute("user", contentUnitService.getUserByPrincipal(principal));
        return "main";
    }


    @GetMapping("/contentUnit/{id}")
    public String contentUnitInfo(@PathVariable Long id, Model model, Principal principal) {
        ContentUnit contentUnit = contentUnitService.getContentUnitById(id);
        model.addAttribute("user", contentUnitService.getUserByPrincipal(principal));
        model.addAttribute("contentUnit", contentUnit);
        model.addAttribute("video", contentUnit.getVideo());
        return "videoplayer";
    }

    @PostMapping("/contentUnit/create")
    public String createProduct(ContentUnit contentUnit, Principal principal, @RequestParam("file1") MultipartFile file1)
            throws IOException {
        contentUnitService.saveContentUnit(principal, contentUnit, file1);
        return "redirect:/my/contentUnits";
    }

    @PostMapping("/contentUnit/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        contentUnitService.deleteContentUnit(id);
        return "redirect:/my/contentUnits";
    }

    @GetMapping("/my/contentUnits")
    public String userProducts(Principal principal, Model model) {
        User user = contentUnitService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("contentUnits", user.getVideos());
        return "create-video";
    }

}