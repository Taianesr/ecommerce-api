package com.teste.ecommerce_api.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

import java.io.IOException;
import java.time.Duration;

@Service
public class BucketService {

    private final S3Client s3Client;

    private final S3Presigner presigner;

    private final SsmClient ssmClient;


    public BucketService(S3Client s3Client, S3Presigner presigner, SsmClient ssmClient) {
        this.s3Client = s3Client;
        this.presigner = presigner;
        this.ssmClient = ssmClient;
    }

    public void uploadImage(String objectName, MultipartFile file) throws IOException {

        String bucketName = getSsmParameter("/ecommerce/aws/s3-bucket-name");


        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(objectName)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar arquivo para o S3", e);
        }

    }

    public byte[] downloadImage(String fileName) {

        String bucketName = getSsmParameter("/ecommerce/aws/s3-bucket-name");


        return s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build(), ResponseTransformer.toBytes()).asByteArray();

    }

    public String generatePreAssigendUrl(String imageName) {

        String bucketName = getSsmParameter("/ecommerce/aws/s3-bucket-name");


        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();

        return presigner.presignGetObject(presignRequest).url()
                .toString();


    }

    private String getSsmParameter(String name) {
        return ssmClient.getParameter(GetParameterRequest.builder()
                        .name(name)
                        .withDecryption(true)
                        .build())
                .parameter()
                .value();
    }



}
