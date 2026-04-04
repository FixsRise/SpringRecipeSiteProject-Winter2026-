package com.testproject.recipesite.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3TestRunner implements CommandLineRunner {

    private final S3Client s3Client;

    public S3TestRunner(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void run(String... args) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket("recipesite-vault")
                        .key("cloud9-test.txt")
                        .build(),
                RequestBody.fromString("Hello from Cloud9")
        );
        System.out.println("UPLOAD OK");
    }
}