package com.example.arclight.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

//https://stackoverflow.com/questions/59898874/enable-authorize-button-in-springdoc-openapi-ui-for-bearer-token-authentication
@Configuration
@OpenAPIDefinition(info = @Info(title = "Arclight", version = "v1"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config // TODO after Spring Security
{

}