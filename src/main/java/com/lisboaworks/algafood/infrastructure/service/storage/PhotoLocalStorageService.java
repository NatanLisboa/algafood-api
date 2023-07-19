package com.lisboaworks.algafood.infrastructure.service.storage;

import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PhotoLocalStorageService implements PhotoStorageService {

    @Value("${algafood.storage.local.photo-directory}")
    private Path photosDirectory;

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            Path filePath = this.getFilePath(newPhoto.getFilename());
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Unable to store file", e);
        }
    }

    private Path getFilePath(String filename) {
        return photosDirectory.resolve(Path.of(filename));
    }

}
