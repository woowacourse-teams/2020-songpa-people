package com.songpapeople.hashtagmap.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Instagram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
    private String hashtagName;
    private Long hashtagCount;

    public Instagram(String hashtagName, Long hashtagCount) {
        this.hashtagName = hashtagName;
        this.hashtagCount = hashtagCount;
    }
}
