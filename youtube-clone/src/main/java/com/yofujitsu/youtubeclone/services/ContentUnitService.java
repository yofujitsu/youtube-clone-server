package com.yofujitsu.youtubeclone.services;

import com.yofujitsu.youtubeclone.dao.entities.ContentUnit;
import com.yofujitsu.youtubeclone.dao.entities.User;
import com.yofujitsu.youtubeclone.dao.entities.Video;
import com.yofujitsu.youtubeclone.dao.repositories.ContentUnitRepository;
import com.yofujitsu.youtubeclone.dao.repositories.UserRepository;
import com.yofujitsu.youtubeclone.dao.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentUnitService {
    @Autowired
    private final ContentUnitRepository contentUnitRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private List<ContentUnit> userVideos = new ArrayList<>();

    public List<ContentUnit> listContentUnits(String title) {
        if (title != null) {
            return contentUnitRepository.findByTitle(title);
        }
        return contentUnitRepository.findAll();
    }

    public List<ContentUnit> listContentUnits(Long user_id) {
        if (user_id != null) {
            return contentUnitRepository.findByUserId(user_id);
        }
        return contentUnitRepository.findAll();
    }

    public void saveContentUnit(Principal principal, ContentUnit contentUnit, String url){
        contentUnit.setUser(getUserByPrincipal(principal));
        Video video1;
        video1 = toVideoEntity(url);
        videoRepository.save(video1);
        contentUnit.addVideoToContent(video1);
        log.info("Saving new ContentUnit. Title: {}; Email: {}", contentUnit.getTitle(), contentUnit.getUser().getEmail());
        ContentUnit contentUnitFromDB = (ContentUnit) contentUnitRepository.save(contentUnit);
        contentUnitRepository.save(contentUnit);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Video toVideoEntity(String url){
        Video video = new Video();
        video.setUrl(url);
        return video;
    }

    public void deleteContentUnit(Long id) {
        ContentUnit contentUnit = contentUnitRepository.findById(id)
                .orElse(null);
        if (contentUnit != null) {
            contentUnitRepository.delete(contentUnit);
        }
    }

    public void likeContentUnit(Long id) {
        ContentUnit contentUnit = contentUnitRepository.findById(id)
                .orElse(null);
        contentUnit.setLikes(contentUnit.getLikes() + 1);
        contentUnitRepository.save(contentUnit);
    }

    public ContentUnit getContentUnitById(Long id) {
        return contentUnitRepository.findById(id).orElse(null);
    }
}