package com.service.patient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation.
 *
 * This class sets up the OpenAPI (Swagger) documentation for the Patient Service API.
 * It provides metadata such as the title, version, and description of the API.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Patient Service API")
                        .version("1.0")
                        .description("API for managing patients"));
    }
}
