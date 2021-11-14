package com.example.jungsunwoung.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_NAME = "JUNGSUNWOUNG API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "SKT 과제 API 명세서";
}
