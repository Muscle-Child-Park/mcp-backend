package com.park.muscle.global.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SecurityScheme(type = SecuritySchemeType.APIKEY, name = "Access-Token", in = SecuritySchemeIn.HEADER)
//@OpenAPIDefinition(security = {@SecurityRequirement(name = "Access-Token")})
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicJwtApi() {
        return GroupedOpenApi.builder()
                .group("JWT")
                .pathsToMatch("/api/jwt/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicLessonApi() {
        return GroupedOpenApi.builder()
                .group("Lesson")
                .pathsToMatch("/api/lesson/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicMemberApi() {
        return GroupedOpenApi.builder()
                .group("Member")
                .pathsToMatch("/api/member/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicProfileApi() {
        return GroupedOpenApi.builder()
                .group("On-boarding")
                .pathsToMatch("/api/profile/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicTicketApi() {
        return GroupedOpenApi.builder()
                .group("Ticket")
                .pathsToMatch("/api/tickets/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicTrainerApi() {
        return GroupedOpenApi.builder()
                .group("Trainer")
                .pathsToMatch("/api/trainers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicReservationApi() {
        return GroupedOpenApi.builder()
                .group("Reservation")
                .pathsToMatch("/api/reserve/**")
                .build();
    }

    @Bean
    public OpenAPI mcParkOpenAPI() {
        String title = "TEAM MC-PARK API DOCS";
        String description = "Mave API end-point Description";
        String version  = "1.0.2";

        return new OpenAPI().addServersItem(new Server().url("/"))
                .components(new Components())
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name("Apache 2.0").url("<http://springdoc.org>")));
    }
}
