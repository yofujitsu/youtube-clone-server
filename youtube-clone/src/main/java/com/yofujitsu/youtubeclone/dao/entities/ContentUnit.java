package com.yofujitsu.youtubeclone.dao.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "users_content")
public class ContentUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 10000)
    private String description;
    @OneToOne
    private Video video;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;
    private int likes;

    @PrePersist
    private void init(){

        dateOfCreated = LocalDateTime.now();
        likes = 0;
    }

    public void addVideoToContent(Video video){
        this.video = video;
        video.setContentUnit(this);
    }
}
