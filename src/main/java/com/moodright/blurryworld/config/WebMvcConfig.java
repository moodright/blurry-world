package com.moodright.blurryworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 * @author moodright
 * @date 2021/3/8
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private UploadConfig uploadConfig;

    @Autowired
    public void setUploadConfig(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    /**
     *  静态资源过滤规则
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地图片物理位置过滤
        registry.addResourceHandler(uploadConfig.getUrlPrefix() + "**").addResourceLocations("file:" + uploadConfig.getStorage());
    }
}
