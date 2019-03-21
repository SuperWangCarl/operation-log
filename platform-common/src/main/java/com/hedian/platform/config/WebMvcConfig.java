package com.hedian.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 添加web配置
 * @param:
 * @return:
 * @auther: SuperWang
 * @date: 2019/3/13 17:16
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	 @Override
	  public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**").allowedOrigins("*")
         .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
         .allowCredentials(true).maxAge(3600);
	  }

}
