package com.vandai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		Contact vandaiContact = new Contact();
		vandaiContact.setName("Vu Van Dai");
		vandaiContact.setUrl("vandaiurl.com");
		vandaiContact.setEmail("vuvandai1103@gmail.com");
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Blog RealTime Api")
			              .description("Blog Realtime Api documentation")
			              .version(appVersion)
			              .license(new License().name("Licese of Api").url("Api licese url"))
			              .contact(vandaiContact)
						)
			              .externalDocs(new ExternalDocumentation()
			              .description("Blog Realtime Wiki Documentation Github")
			              .url("https://github.com/vandaihust/blogrealtime.git"));
	}
	
	
	
}
