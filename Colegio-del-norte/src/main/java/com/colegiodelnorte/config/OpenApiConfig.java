package com.colegiodelnorte.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI colegioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Colegio - Gestión Escolar")
                        .description("API para gestión de alumnos, docentes, cursos y más")
                        .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("colegio-api")
                .pathsToMatch("/api/**")
                .build();
    }
}