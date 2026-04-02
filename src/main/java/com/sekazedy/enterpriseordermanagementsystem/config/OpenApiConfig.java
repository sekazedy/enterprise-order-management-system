package com.sekazedy.enterpriseordermanagementsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Enterprise Order Management System API")
                        .version("1.0")
                        .description("REST API for Enterprise Order Management System educational project")
                        .contact(new Contact()
                                .name("Sergejs Černišenko")
                                .email("sekazedy@gmail.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/license/mit"))
                );
    }
}
