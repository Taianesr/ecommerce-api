package com.teste.ecommerce_api.demo.controller;

import com.teste.ecommerce_api.demo.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String objectName) throws IOException {

        log.info("Upload image request received. Object name: {}", objectName);

            bucketService.uploadImage(objectName, file);

            return ResponseEntity
                    .ok("Imagem enviada com sucesso: " + objectName);

    }


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadImage(@RequestParam("fileName") String fileName) {

        log.info("Download image request received. File name: {}", fileName);

        byte[] image = bucketService.downloadImage(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);

    }
}
