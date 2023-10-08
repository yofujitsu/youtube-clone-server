package com.yofujitsu.youtubeclone.dao.entities;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "videofiles")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ContentUnit contentUnit;
}
