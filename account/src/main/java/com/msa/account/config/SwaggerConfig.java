package com.msa.account.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "유저 API",
                description = "유저 API docs"
        )
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

}
