package com.yofujitsu.youtubeclone.dao.entities;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users_content")
public class ContentUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 10000)
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Video video;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private User user;
    private LocalDateTime dateOfCreated;
    private int watches;
    private int likes;
    private String thumbnailUrl;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
        watches = 0;
        likes = 0;
    }

    public void addVideoToContent(Video video){
        this.video = video;
        video.setContentUnit(this);
    }

    public String updateDateDesc() {
        LocalDateTime currentDate = LocalDateTime.now();
        Duration duration = Duration.between(this.dateOfCreated, currentDate);

        if (duration.toDays() == 0) {
            return "сегодня";
        } else if (duration.toDays() == 1) {
            return "вчера";
        } else if (duration.toDays() < 30) {
            return duration.toDays() + " дней назад";
        } else {
            long months = duration.toDays() / 30;
            return months + " месяцев назад";
        }
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        this.likes--;
    }

    @Override
    public String toString() {
        return "ContentUnit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreated=" + dateOfCreated +
                ", watches=" + watches +
                ", likes=" + likes +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContentUnit that = (ContentUnit) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
