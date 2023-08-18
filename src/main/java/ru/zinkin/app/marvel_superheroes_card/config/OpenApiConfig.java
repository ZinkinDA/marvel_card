package ru.zinkin.app.marvel_superheroes_card.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Marvel card application API",
                description = "worked card",
                contact = @Contact(
                        name = "Zinkin Dmitry \\ E5CAPE14",
                        email = "zinkin.dmitry@gmail.com"
                ),
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}
