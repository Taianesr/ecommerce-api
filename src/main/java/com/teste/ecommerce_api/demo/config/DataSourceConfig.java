package com.teste.ecommerce_api.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final SsmClient ssmClient;

    public DataSourceConfig(SsmClient ssmClient) {
        this.ssmClient = ssmClient;
    }

    @Bean
    public DataSource dataSource() {
        String url = getParameter("/ecommerce-api/db-aiven/url");
        String username = getParameter("/ecommerce-api/db-aiven/user");
        String password = getParameter("/ecommerce-api/db-aiven/password", true);
        String driver = getParameter("/ecommerce-db/driver-class-name");

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    private String getParameter(String name) {
        return getParameter(name, false);
    }

    private String getParameter(String name, boolean decrypt) {
        return ssmClient.getParameter(GetParameterRequest.builder()
                        .name(name)
                        .withDecryption(decrypt)
                        .build())
                .parameter()
                .value();
    }


}
