package com.shane.mybatis.config;

import com.shane.mybatis.dto.ResponseStatus;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket openApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Test Group")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameter())
                .globalResponses(HttpMethod.GET, getGlobalResponse());
    }

    private List<Response> getGlobalResponse() {
        return ResponseStatus.HTTP_STATUS_ALL.stream().map(
                a -> new ResponseBuilder().code(a.getResponseCode()).description(a.getDescription()).build()
        ).collect(Collectors.toList());
    }

    private List<RequestParameter> getGlobalRequestParameter() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("AppKey")
                .description("App key")
                .required(false)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build()
        );
        return parameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("mybatis api")
                .version("1.0")
                .build();
    }
}
