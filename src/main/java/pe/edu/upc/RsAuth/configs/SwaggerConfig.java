package pe.edu.upc.RsAuth.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket AuthApi() {
        //Adding Header
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("pe.edu.upc.RsAuth.controllers"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(aParameters);
    }

    //@Bean
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Smart Finance Document API",
                "\"API documentation for integration with your Finance\"",
                "1.0.0",
                "UPC License Version 1.0",
                new Contact("Paolo Ortega", "http://www.upc.edu.pe/", "u201512937@upc.edu.pe"),
                "UPC License Version 1.0", "http://www.upc.edu.pe/", Collections.emptyList());
    }
}
