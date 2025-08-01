package com.bothty.mobilebankingjpa.dto.media;

import lombok.Builder;

@Builder
public record MediaResponse(
        String name,
        String mimeType,
        String imageUrl,
        Long size
) {
}
