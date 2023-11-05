package com.yofujitsu.youtubeclone.dao.entities;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10000)
    private String url;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "content_unit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_video_content_unit"))
    private ContentUnit contentUnit;
}
