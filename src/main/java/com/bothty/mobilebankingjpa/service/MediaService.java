package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.media.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {

    MediaResponse upload(MultipartFile file) throws IOException;

}
