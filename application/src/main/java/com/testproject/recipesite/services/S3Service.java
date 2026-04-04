package com.testproject.recipesite.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file) {

        String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            s3Client.putObject(
                    request,
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }

        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
