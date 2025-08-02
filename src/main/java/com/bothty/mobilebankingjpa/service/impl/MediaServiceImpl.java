package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Media;
import com.bothty.mobilebankingjpa.dto.media.MediaResponse;
import com.bothty.mobilebankingjpa.repository.MediaRepository;
import com.bothty.mobilebankingjpa.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {


    @Value("${media.server.path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    private final MediaRepository mediaRepository;

    @Override
    public List<MediaResponse> upload(List<MultipartFile> files) throws IOException {

        List<MediaResponse> responses = new ArrayList<>();

        for (MultipartFile file : files){

            // Generate Name
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
            media.setMimeType(file.getContentType());
            media.setExtension(extension);
            media.setIsDeleted(false);

            mediaRepository.save(media);

            MediaResponse mediaResponse = MediaResponse.builder()
                    .name(media.getName())
                    .imageUrl(baseUri + String.format("%s.%s", name, extension))
                    .size(file.getSize())
                    .mimeType(media.getMimeType())
                    .build();

            responses.add(mediaResponse);
        }

        return responses;
    }

    @Override
    public List<MediaResponse> getAllMedia() throws IOException {

        File folder = new File(serverPath);
        List<MediaResponse> responses = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles();

            //Loop each file
            if (files != null){
                for (File file : files){
                    if (file.isFile()){
                        responses.add(MediaResponse.builder()
                                        .name(file.getName())
                                        .mimeType(Files.probeContentType(file.toPath()))
                                        .imageUrl(baseUri + String.format("%s.%s", file.getName(), file.getName()))
                                        .size(file.length())
                                .build());
                    }
                }
            }
        }

        return responses;
    }

    @Override
    public Resource loadMediaResource(String mediaName) throws IOException {

        Path fileName = Paths.get(serverPath).resolve(mediaName).normalize();

        if(!Files.exists(fileName) || !Files.isRegularFile(fileName)){
            throw new IOException("Media not found with name: " + mediaName);
        }

        return new UrlResource(fileName.toUri());
    }
}
