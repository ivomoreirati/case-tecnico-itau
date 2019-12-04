package br.com.itau.renegociate.billing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuração para o Swagger
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.itau.renegociate.billing"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInformation());
        // @formatter:on
    }

    private ApiInfo getApiInformation(){
        return new ApiInfo("Agentes de cobrança REST API",
                "This is a API created using Spring Boot",
                "1.0",
                "API Terms of Service URL",
                new Contact("Progressive Coder", null, "ivo.moreira.ti@gmail.com"),
                null,
                null,
                Collections.emptyList()
        );
    }
}
