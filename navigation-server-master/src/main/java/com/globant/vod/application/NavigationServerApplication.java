package com.globant.vod.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author rohitkumar.patel
 */
@SpringBootApplication(scanBasePackages = {"com.globant.vod.*" })
/* @EnableDiscoveryClient */
@EnableAutoConfiguration
public class NavigationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavigationServerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(com.globant.vod.utils.RestTemplateConfigurations.getClientHttpRequestFactory(9000));
    }
}
