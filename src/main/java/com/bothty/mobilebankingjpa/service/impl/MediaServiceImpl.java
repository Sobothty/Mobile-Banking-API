package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Media;
import com.bothty.mobilebankingjpa.dto.media.MediaResponse;
import com.bothty.mobilebankingjpa.repository.MediaRepository;
import com.bothty.mobilebankingjpa.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {


    @Value("media.server.path")
    private String serverPath;

    @Value("media.base-uri")
    private String baseUri;

    private final MediaRepository mediaRepository;

    @Override
    public MediaResponse upload(MultipartFile file) throws IOException {


        String name = UUID.randomUUID().toString();

        //Find Extension
        int lastIndex = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

        //Substring to get extension
        String extension = file.getOriginalFilename()
                .substring(lastIndex + 1);

        //Create path
        Path path = Paths.get(serverPath + String.format("%s.%s", name, extension));

        try{
            Files.copy(file.getInputStream(), path);
        }catch (Exception e){
            e.printStackTrace();
        }

        Media media = new Media();
        media.setName(name);
        media.setImageUrl(String.valueOf(path));
        media.setMimeType(file.getOriginalFilename());
        media.setExtension(extension);
        media.setIsDeleted(false);

        mediaRepository.save(media);

        return MediaResponse.builder()
                .name(media.getName())
                .imageUrl(media.getImageUrl())
                .size(file.getSize())
                .mimeType(media.getMimeType())
                .build();
    }
}
