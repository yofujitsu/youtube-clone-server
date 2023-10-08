package com.yofujitsu.youtubeclone.dao.repositories;

import com.yofujitsu.youtubeclone.dao.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
