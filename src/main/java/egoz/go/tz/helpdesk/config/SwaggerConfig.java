package egoz.go.tz.helpdesk.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		ApiInfoBuilder builder = new ApiInfoBuilder().title("eGOZ HelpDesk REST-API")
				.description("Documentation automatically generated").version("1.0.0")
				.contact(new Contact("eGOZ Developers", "egoz.go.tz", "developers@egoz.go.tz"));
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("egoz.go.tz.helpdesk.web"))
				.paths(PathSelectors.any()).build()				
				//.securitySchemes(Arrays.asList(securityScheme()))
				//.securityContexts(Arrays.asList(securityContext()))
				.apiInfo(builder.build());
	}


	private SecurityScheme securityScheme() {
		return new ApiKey("bearerToken", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference("bearerToken", scopes())))
				.forPaths(PathSelectors.any()).build();
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = {};
		return scopes;
	}
}
