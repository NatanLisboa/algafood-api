package com.lisboaworks.algafood.core.storage;

import com.lisboaworks.algafood.core.storage.StorageProperties.StorageType;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.LocalPhotoStorageService;
import com.lisboaworks.algafood.infrastructure.service.storage.S3PhotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StorageConfig {

    private final StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                storageProperties.getS3().getAccessKeyId(),
                storageProperties.getS3().getSecretAccessKey()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public PhotoStorageService photoStorageService() {
        if (storageProperties.getStorageType().equals(StorageType.LOCAL)) {
            return new LocalPhotoStorageService();
        } else {
            return new S3PhotoStorageService();
        }
    }

}
