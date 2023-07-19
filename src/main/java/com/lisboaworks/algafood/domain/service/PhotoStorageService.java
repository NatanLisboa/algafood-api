package com.lisboaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    void store(NewPhoto newPhoto);

    default String generateNewFilename(String originalFilename) {
        return UUID.randomUUID() + "_" + originalFilename;
    }

    @Getter
    @Builder
    class NewPhoto {

        private String filename;
        private InputStream inputStream;

    }

}
