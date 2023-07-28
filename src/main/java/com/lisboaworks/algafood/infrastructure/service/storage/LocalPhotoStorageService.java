package com.lisboaworks.algafood.infrastructure.service.storage;

import com.lisboaworks.algafood.core.storage.StorageProperties;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//@Service
@AllArgsConstructor
public class LocalPhotoStorageService implements PhotoStorageService {

    private final StorageProperties storageProperties;

    @Override
    public RetrievedPhoto get(String filename) {
        try {
            Path filePath = this.getFilePath(filename);
            RetrievedPhoto retrievedPhoto = RetrievedPhoto.builder()
                    .inputStream(Files.newInputStream(filePath))
                    .build();
            return retrievedPhoto;
        } catch (Exception e) {
            throw new StorageException("Unable to get file", e);
        }
    }

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            Path filePath = this.getFilePath(newPhoto.getFilename());
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Unable to store file", e);
        }
    }

    @Override
    public void remove(String filename) {
        try {
            Path filePath = this.getFilePath(filename);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Unable to delete file", e);
        }
    }

    private Path getFilePath(String filename) {
        return storageProperties.getLocal().getPhotosDirectory().resolve(Path.of(filename));
    }

}
