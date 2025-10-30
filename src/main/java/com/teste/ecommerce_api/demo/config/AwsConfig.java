package com.teste.ecommerce_api.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Configuration
public class AwsConfig {

    private final SsmClient ssmClient;

    public AwsConfig(SsmClient ssmClient) {
        this.ssmClient = ssmClient;
    }

    @Bean
    public S3Client s3Client() {
        String accessKey= getParameter("/ecommerce/aws/access-key-id");
        String secretKey= getParameter("/ecommerce/aws/secret-access-key");

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.US_EAST_1)
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        String accessKey= getParameter("/ecommerce/aws/access-key-id");
        String secretKey= getParameter("/ecommerce/aws/secret-access-key");

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Presigner.builder()
                .region(Region.of(String.valueOf(Region.US_EAST_1)))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }


    private String getParameter(String name) {
        return ssmClient.getParameter(GetParameterRequest.builder()
                        .name(name)
                        .withDecryption(true)
                        .build())
                .parameter()
                .value();
    }


}
