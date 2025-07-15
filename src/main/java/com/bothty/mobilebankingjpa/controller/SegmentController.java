package com.bothty.mobilebankingjpa.controller;


import com.bothty.mobilebankingjpa.dto.segment.SegmentRequestResponse;
import com.bothty.mobilebankingjpa.service.SegmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/segments")
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping
    public ResponseEntity<?> getAllSegment(){
        return new ResponseEntity<>(
                Map.of(
                        "message", "All Segment Data",
                        "data", segmentService.getAllSegment(),
                        "timestamp", LocalDateTime.now()
                ), HttpStatus.OK
        );
    }


    @PostMapping
    public ResponseEntity<?> createNewSegment(@Valid @RequestBody SegmentRequestResponse segmentRequestResponse){
        return new ResponseEntity<>(
                Map.of(
                        "message", "Segment is created",
                        "segment", segmentService.createNewSegment(segmentRequestResponse),
                        "timestamp", LocalDateTime.now()
                ), HttpStatus.OK
        );
    }
}
