package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {
    boolean existsSegmentById(Integer id);

    Optional<Segment> findBySegmentName(String segmentName);
}
