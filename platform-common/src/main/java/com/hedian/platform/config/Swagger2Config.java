package com.hedian.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: @Configuration是表示这是一个配置类，是JDK自带的注解
 * 				@EnableSwagger2的作用是启用Swagger2相关功能。
 * 访问地址 : http://localhost:8080/platform/swagger-ui.html
 * @param:
 * @return:
 * @auther: SuperWang
 * @date: 2019/3/11 16:54
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.hedian.platform"))
				//.paths(PathSelectors.regex("/rest/.*"))
				.paths(PathSelectors.any())//匹配何种路径时显示
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("镇江项目 API") //大标题
				.description("镇江系统Restful API") //小标题
				//.termsOfServiceUrl("http://127.0.0.1:8181/")
				//.contact("liuxiaopeng")
				.version("1.0")
				.build();
	}
}