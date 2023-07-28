package com.lisboaworks.algafood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lisboaworks.algafood.core.storage.StorageProperties;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class S3PhotoStorageService implements PhotoStorageService {

    private final AmazonS3 amazonS3;
    private final StorageProperties storageProperties;

    @Override
    public InputStream get(String filename) {
        return null;
    }

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            String filePath = this.getFilePath(newPhoto.getFilename());

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    filePath,
                    newPhoto.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to store file in Amazon S3", e);
        }
    }

    @Override
    public void remove(String filename) {
        try {
            String filePath = this.getFilePath(filename);
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), filePath);
            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to remove file from Amazon S3 bucket", e);
        }
    }

    private String getFilePath(String filename) {
        return String.format("%s/%s", storageProperties.getS3().getPhotosDirectory(), filename);
    }

}
