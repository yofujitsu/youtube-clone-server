package com.yofujitsu.youtubeclone.controllers;

import com.yofujitsu.youtubeclone.dao.entities.Video;
import com.yofujitsu.youtubeclone.dao.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;
    @GetMapping("/videos/{id}")
    private ResponseEntity<?> getVideoById(@PathVariable Long id){
        Video video = videoRepository.findById(id).orElse(null);
        return ResponseEntity.ok().header("fileName", video.getOriginalFileName())
                .contentType(MediaType.valueOf(video.getContentType()))
                .contentLength(video.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(video.getBytes())));
    }
}