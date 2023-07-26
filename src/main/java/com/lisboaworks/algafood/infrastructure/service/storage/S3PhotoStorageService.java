package com.lisboaworks.algafood.infrastructure.service.storage;

import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

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
