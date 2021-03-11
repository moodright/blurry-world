package com.moodright.blurryworld.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 头像上传配置类
 * @author moodright
 * @date 2021/3/11
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "upload.avatar.path")
public class AvatarUploadConfig {
    /**
     * 存储文件物理位置
     */
    private String storage;
    /**
     * 主机名
     */
    private String host;
    /**
     * 头像上传url前缀
     */
    private String urlPrefix = "/public/avatar/";

    @PostConstruct
    public void init() throws IOException {
        FileUtils.forceMkdir(new File(this.getStorage()));
    }

}
