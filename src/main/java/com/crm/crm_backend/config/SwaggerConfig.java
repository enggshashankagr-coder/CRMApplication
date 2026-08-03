package com.crm.crm_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI crmOpenAPI() {

		final String securitySchemeName = "bearerAuth";

		return new OpenAPI()

				.info(new Info().title("CRM Backend API").description("Enterprise CRM REST APIs").version("v1.0.0")

						.contact(new Contact().name("Shashank Agrahari").email("support@crm.com"))

						.license(new License().name("Private License")))

				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

				.schemaRequirement(securitySchemeName,

						new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT"));

	}

}