package com.teste.ecommerce_api.demo.config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.teste.ecommerce_api.demo.service.AwsParameterCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Configuration
public class PaypalConfiguration {


    private final AwsParameterCache awsParameterCache;

    public PaypalConfiguration(AwsParameterCache awsParameterCache) {
        this.awsParameterCache = awsParameterCache;
    }


    @Bean
    public PayPalHttpClient getPaypalClient() {

        String clientId = awsParameterCache.get("/ecommerce-api/paypal/clientId");
        String clientSecret = awsParameterCache.get("/ecommerce-api/paypal/clientSecret");

        return new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));

    }


}
