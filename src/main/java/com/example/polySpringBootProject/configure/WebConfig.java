package com.example.polySpringBootProject.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 뷰에서 resourcePath를 경로로 써도 savePath로 매핑됨
    private String resourcePath = "/upload/**";  // view 에서 접근할 경로
    private String savePath = "file:///C:/springboot_img/";  // 실제 파일 저장

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
