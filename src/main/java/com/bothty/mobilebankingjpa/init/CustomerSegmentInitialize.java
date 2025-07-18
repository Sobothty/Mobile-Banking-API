package com.bothty.mobilebankingjpa.init;

import com.bothty.mobilebankingjpa.domain.Segment;
import com.bothty.mobilebankingjpa.repository.SegmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final SegmentRepository segmentRepository;

    @PostConstruct
    public void init(){
        if (segmentRepository.count() == 0){
            Segment regular = new Segment();
            regular.setSegmentName("REGULAR");
            Segment silver = new Segment();
            silver.setSegmentName("SILVER");
            Segment gold = new Segment();
            gold.setSegmentName("GOLD");

            segmentRepository.saveAll(
                    List.of(regular, silver, gold)
            );
        }
    }
}
