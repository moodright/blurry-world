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
 * @author moodright
 * @date 2021/3/18
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "upload.cover.path")
public class CoverUploadConfig {
    /**
     * 存储文件物理位置
     */
    private String storage;
    /**
     * 主机名
     */
    private String host;
    /**
     * 文章封面url前缀
     */
    private String urlPrefix = "/public/post/cover/";

    @PostConstruct
    public void init() throws IOException {
        FileUtils.forceMkdir(new File(this.getStorage()));
    }

}
