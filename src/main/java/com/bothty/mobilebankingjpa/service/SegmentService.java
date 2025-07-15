package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.segment.SegmentRequestResponse;

import java.util.List;

public interface SegmentService {

    List<SegmentRequestResponse> getAllSegment();
    SegmentRequestResponse createNewSegment(SegmentRequestResponse segmentRequestResponse);
}
