package com.bothty.mobilebankingjpa.controller;


import com.bothty.mobilebankingjpa.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medias")
public class MediaController {

    private final MediaService mediaService;


    @GetMapping
    public ResponseEntity<?> getAllMedia() throws IOException {
        return new ResponseEntity<>(
                Map.of(
                        "message","All Media",
                        "media", mediaService.getAllMedia()
                ), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam List<MultipartFile> file) throws IOException {

        return new ResponseEntity<>(
                Map.of(
                        "data", mediaService.upload(file),
                        "status", "success"
                ), HttpStatus.OK
        );
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadMedia(@RequestParam("name") String mediaName) throws IOException {

        try{
            Resource resource = mediaService.loadMediaResource(mediaName);
            String contentType = Files.probeContentType(Path.of(resource.getFile().getPath()));

            if(contentType == null){
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
