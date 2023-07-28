package com.lisboaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

public interface PhotoStorageService {

    RetrievedPhoto get(String filename);

    void store(NewPhoto newPhoto);

    void remove(String filename);

    default String generateNewFilename(String originalFilename) {
        return UUID.randomUUID() + "_" + originalFilename;
    }

    default void replace(String oldFilename, NewPhoto newPhoto) {
        this.store(newPhoto);

        if (Objects.nonNull(oldFilename)) {
            this.remove(oldFilename);
        }
    }

    @Getter
    @Builder
    class NewPhoto {

        private String filename;
        private String contentType;
        private InputStream inputStream;

    }

    @Getter
    @Builder
    class RetrievedPhoto {

        private InputStream inputStream;
        private String url;

        public boolean hasUrl() {
            return Objects.nonNull(this.url);
        }

        public boolean hasInputStream() {
            return Objects.nonNull(this.inputStream);
        }

    }

}
