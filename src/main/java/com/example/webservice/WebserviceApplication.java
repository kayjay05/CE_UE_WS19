package com.example.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WebserviceApplication {

    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }

    /*
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String products = restTemplate.getForObject(
                    "https://www.maripavi.at/produkt/griff?material=Carbon", String.class);
            log.info(String.valueOf(products));
        };
    }
*/
}

/*
https://www.maripavi.at/api/account
https://www.maripavi.at/api/bestellung
https://www.maripavi.at/api/produkt
*/

