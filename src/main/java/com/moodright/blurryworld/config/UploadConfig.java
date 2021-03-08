package com.moodright.blurryworld.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传配置类
 * @author moodright
 * @date 2021/3/8
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "upload.file.path")
public class UploadConfig {
    /**
     * 存储文件物理位置
     */
    private String storage;
    /**
     * 主机名
     */
    private String host;
    /**
     * url前缀
     */
    private String urlPrefix = "/public/image/";

    @PostConstruct
    public void init() throws IOException {
        FileUtils.forceMkdir(new File(this.getStorage()));
    }

}
