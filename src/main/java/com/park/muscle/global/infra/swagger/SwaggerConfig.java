package com.park.muscle.global.infra.swagger;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Mock-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.park.muscle.mock"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket nonSecurityApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Non-Security API")
                .select()
                .apis(withoutMethodAnnotation(PreAuthorize.class))
                .apis(RequestHandlerSelectors.basePackage("com.park.muscle.core"))
                .apis(RequestHandlerSelectors.basePackage("com.park.muscle"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket securedApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Security API")
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(PreAuthorize.class))
                .apis(RequestHandlerSelectors.basePackage("com.park.muscle.core"))
                .build()
                .apiInfo(apiInfo());
    }

    public static Predicate<RequestHandler> withoutMethodAnnotation(
            final Class<? extends Annotation> annotation) {
        return input -> !input.isAnnotatedWith(annotation);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Bearer", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MC-PARK Swagger API")
                .description("Team Muscle Child Park Swagger docs")
                .version("1.0")
                .build();
    }
}
