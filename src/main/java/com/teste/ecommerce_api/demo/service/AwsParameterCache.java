package com.teste.ecommerce_api.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwsParameterCache {

    private final Map<String, String> cache = new HashMap<>();
    private final SsmClient ssmClient;

    public AwsParameterCache(SsmClient ssmClientProvider) {
        this.ssmClient = ssmClientProvider;
    }

    @PostConstruct
    private void init() {

        List<String> paths = List.of(
                "/ecommerce-api/paypal",
                "/ecommerce-db/",
                "/ecommerce-api/db-aiven/",
                "/ecommerce-api/db-aurora-url",
                "/ecommerce-api/db-admin",
                "/ecommerce/aws/"
        );

        for (String path : paths) {
            ssmClient.getParametersByPath(GetParametersByPathRequest.builder()
                            .path(path)
                            .recursive(true)
                            .withDecryption(true)
                            .build())
                    .parameters()
                    .forEach(p -> cache.put(p.name(), p.value()));
        }
    }

    public String get(String name) {
        return cache.get(name);
    }
}


