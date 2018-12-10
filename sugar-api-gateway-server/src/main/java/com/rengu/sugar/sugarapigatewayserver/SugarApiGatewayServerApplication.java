package com.rengu.sugar.sugarapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SugarApiGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SugarApiGatewayServerApplication.class, args);
    }
}
