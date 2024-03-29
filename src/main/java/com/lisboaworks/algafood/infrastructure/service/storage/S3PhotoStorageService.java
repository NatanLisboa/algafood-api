package com.lisboaworks.algafood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lisboaworks.algafood.core.storage.StorageProperties;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public RetrievedPhoto get(String filename) {
        String filePath = this.getFilePath(filename);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), filePath);
        return RetrievedPhoto.builder()
                .url(url.toString())
                .build();
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
