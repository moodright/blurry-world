package com.moodright.blurryworld.config;

import com.sun.deploy.ui.JavaTrayIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 * @author moodright
 * @date 2021/3/8
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private UploadConfig uploadConfig;
    private AvatarUploadConfig avatarUploadConfig;
    private CoverUploadConfig coverUploadConfig;

    @Autowired
    public void setUploadConfig(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }
    @Autowired
    public void setAvatarUploadConfig(AvatarUploadConfig avatarUploadConfig) {
        this.avatarUploadConfig = avatarUploadConfig;
    }
    @Autowired
    public void setCoverUploadConfig(CoverUploadConfig coverUploadConfig) {
        this.coverUploadConfig = coverUploadConfig;
    }

    /**
     * 视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 拦截器模板
        registry.addViewController("/interceptor").setViewName("user/interceptor");
    }

    /**
     * 静态资源过滤规则
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地图片物理位置过滤
        registry.addResourceHandler(uploadConfig.getUrlPrefix() + "**").addResourceLocations("file:" + uploadConfig.getStorage());
        // 本地头像物理位置过滤
        registry.addResourceHandler(avatarUploadConfig.getUrlPrefix() + "**").addResourceLocations("file:" + avatarUploadConfig.getStorage());
        // 本地文章封面物理位置过滤
        registry.addResourceHandler(coverUploadConfig.getUrlPrefix() + "**").addResourceLocations("file:" + coverUploadConfig.getStorage());
    }

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/login","/register/**","/interceptor",
                                    "/css/**","/js/**","/images/**","/lib/**");
    }
}
