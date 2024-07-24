package com.service.note.config;

import com.service.note.exceptions.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for OpenAPI documentation.
 *
 * This class sets up the OpenAPI (Swagger) documentation for the Note Service API.
 * It provides metadata such as the title, version, and description of the API.
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Note Service API")
                        .version("1.0")
                        .description("API for managing note"));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}