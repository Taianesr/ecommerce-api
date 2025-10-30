package com.teste.ecommerce_api.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class PaypalAuthService {

    private final AwsParameterCache awsParameterCache;

    private static final String tokenUrl = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

    public PaypalAuthService(SsmClient ssmClient, AwsParameterCache awsParameterCache) {
        this.awsParameterCache = awsParameterCache;
    }

    public String fetchPaypalAccessToken() throws JsonProcessingException {


        String clientId = awsParameterCache.get("/ecommerce-api/paypal/clientId");
        String clientSecret = awsParameterCache.get("/ecommerce-api/paypal/clientSecret");


        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedAuth);


        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode node = mapper.readTree(response.getBody());
            String accessToken = node.get("access_token").asText();

            return accessToken;

        } else {
            throw new RuntimeException("Erro ao autenticar no PayPal: " + response.getStatusCode());
        }
    }

}

