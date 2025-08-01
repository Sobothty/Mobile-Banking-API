package com.bothty.mobilebankingjpa.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String imageUrl;

    @Column(nullable = false, length = 50)
    private String mimeType;

    @Column(nullable = false, length = 20)
    private String extension;

    @Column(nullable = false)
    private Boolean isDeleted;
}
