package com.rengu.sugar.sugareurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SugarEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SugarEurekaServerApplication.class, args);
    }
}
