package com.bothty.mobilebankingjpa.controller;


import com.bothty.mobilebankingjpa.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medias")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public ResponseEntity<?> uploadFile(MultipartFile file) throws IOException {
        return new ResponseEntity<>(
                Map.of(
                        "data", mediaService.upload(file),
                        "status", "success"
                ), HttpStatus.OK
        );
    }
}
