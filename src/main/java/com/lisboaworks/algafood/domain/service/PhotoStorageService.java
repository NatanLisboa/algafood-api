package com.lisboaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface PhotoStorageService {

    void store(NewPhoto newPhoto);

    @Getter
    @Builder
    class NewPhoto {

        private String filename;
        private InputStream inputStream;

    }

}
