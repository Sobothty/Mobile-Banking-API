package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.media.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    List<MediaResponse> upload(List<MultipartFile> file) throws IOException;
    List<MediaResponse> getAllMedia() throws IOException;
    Resource loadMediaResource(String mediaName) throws IOException;
}
