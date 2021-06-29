package com.datum.houserent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @author datum
 * @date 2021/6/22 21:27
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    @Bean
    public Docket apiUser() {
        return docket(apiInfo(),
                "租房者",
                "/**/user/**","/**/common/**/");
    }

    @Bean
    public Docket apiLessor() {
        return docket(apiInfo(),
                "出租者",
                "/**/lessor/**", "/**/common/**/");
    }

    private Docket docket(ApiInfo apiInfo, String groupName,
                          String... antPaths) {
        Predicate<String> pathSelector = PathSelectors.ant("");
        for (String antPath : antPaths) {
            pathSelector = pathSelector.or(PathSelectors.ant(antPath));
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.datum.houserent.controller"))
                .paths(pathSelector)
                .build();
    }

    /**
     * api基本信息
     *
     * @return api基本信息对象
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("租房项目")
                .description("租房项目api文档")
                .termsOfServiceUrl("http://localhost:8888/house-rent/doc.html")
                .version("1.0")
                .build();
    }
}
