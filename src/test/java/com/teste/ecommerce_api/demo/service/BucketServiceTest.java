package com.teste.ecommerce_api.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.io.IOException;

import static org.mockito.Mockito.when;

//class BucketServiceTest {
//
//    @Mock
//    private S3Client s3Client;
//
//    @Mock
//    private S3Presigner presigner;
//
//    @Mock
//    private SsmClient ssmClient;
//
//    private BucketService bucketService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        bucketService = new BucketService(s3Client, presigner, ssmClient);
//    }
//
//    public void shouldUploadImageSuccessfully() throws IOException {
//
//        MultipartFile file = new MockMultipartFile(
//                "file",                          // nome do campo
//                "image.jpg",                     // nome do arquivo
//                "image/jpeg",                    // tipo de conteúdo
//                "conteudo fake da imagem".getBytes() // bytes do arquivo
//        );
//
//        String bucketName = "ecommerce-test-bucket";
//
//        PutObjectResponse mockResponse = PutObjectResponse.builder()
//                .eTag("mock-etag-123")
//                .build();
//
//        var response=  when(s3Client.putObject(  PutObjectRequest.builder()
//                        .bucket(bucketName)
//                        .key("teste")
//                        .build(),
//                RequestBody.fromInputStream(file.getInputStream(), file.getSize()))).thenReturn();
//
//    }


//
//}