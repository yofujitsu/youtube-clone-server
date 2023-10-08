package com.yofujitsu.youtubeclone.dao.repositories;

import com.yofujitsu.youtubeclone.dao.entities.ContentUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentUnitRepository extends JpaRepository<ContentUnit, Long> {
    List<ContentUnit> findByTitle(String title);
}
