package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Segment;
import com.bothty.mobilebankingjpa.dto.segment.SegmentRequestResponse;
import com.bothty.mobilebankingjpa.repository.SegmentRepository;
import com.bothty.mobilebankingjpa.service.SegmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SegmentServiceImpl implements SegmentService {

    private final SegmentRepository segmentRepository;

    @Override
    public List<SegmentRequestResponse> getAllSegment() {
        return segmentRepository.findAll()
                .stream().map(segment -> new SegmentRequestResponse(
                        segment.getSegmentName()
                )).toList();
    }

    @Override
    public SegmentRequestResponse createNewSegment(SegmentRequestResponse segmentRequestResponse) {

        Segment segment = new Segment();

        segment.setSegmentName(segmentRequestResponse.segmentName());

        segmentRepository.save(segment);

        return new SegmentRequestResponse(
                segmentRequestResponse.segmentName()
        );
    }
}
