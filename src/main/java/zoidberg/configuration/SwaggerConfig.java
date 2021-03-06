package zoidberg.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(or(basePackage("zoidberg.controller"), basePackage("org.springframework.boot.actuate.endpoint")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Zoidberg",
                "Zoidberg is intended for embedding a JRuby console in a Java web application" +
                        "which then could access arbitrary Java services using the Ruby programming language.",
                "0.3-SNAPSHOT",
                null,
                null,
                null,
                null,
                Collections.emptyList());
        return apiInfo;
    }
}
