package com.lisboaworks.algafood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class S3PhotoStorageService implements PhotoStorageService {

    private final AmazonS3 amazonS3;

    @Override
    public InputStream get(String filename) {
        return null;
    }

    @Override
    public void store(NewPhoto newPhoto) {

    }

    @Override
    public void remove(String filename) {

    }

}
