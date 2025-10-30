package com.teste.ecommerce_api.demo.controller;

import com.teste.ecommerce_api.demo.service.BucketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String objectName) throws IOException {
        bucketService.uploadImage(objectName, file);

        try {
            bucketService.uploadImage(objectName, file);
            return ResponseEntity
                    .ok("Imagem enviada com sucesso: " + objectName);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar a imagem: " + e.getMessage());
        }

    }


    @GetMapping("/downloadImage")
    public ResponseEntity<byte[]> downloadImage(@RequestParam("fileName") String fileName) throws IOException {
        byte[] image = bucketService.downloadImage(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);

    }
}
