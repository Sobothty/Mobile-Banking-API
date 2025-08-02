package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {
    Optional<Media> findByName(String name);
}
