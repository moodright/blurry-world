package com.moodright.blurryworld.controller.utils;

import com.moodright.blurryworld.config.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.joda.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传控制器
 * @author moodright
 * @date 2021/3/7
 */
@Controller
@RequestMapping("picture")
public class PictureUploadController {

    private UploadConfig uploadConfig;

    @Autowired
    public void setUploadConfig(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    /**
     * editormd 图片上传接口
     * @param file 上传的图片
     * @return JSON字符串
     *         {
     *              success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     *              message : "提示的信息，上传成功或上传失败及错误信息等。",
     *              url     : "图片地址"        // 上传成功时才返回
     *         }
     */
    @PostMapping(value = "upload")
    @ResponseBody
    public Map<String, Object> pictureUpload(@RequestParam(value = "editormd-image-file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try{
            // 获取文件名
            String originalFileName = file.getOriginalFilename();
            // 获取文件拓展名
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 定义保存在本地的文件名（防止文件命名冲突）
            String saveFileName = LocalDateTime.now().toString("yyyyMMddHHmmss") + Thread.currentThread().getId() + extensionName;
            // 创建 物理位置+文件名 路径
            Path path = Paths.get(uploadConfig.getStorage(), saveFileName);
            // 写入文件比特流至具体位置
            Files.write(path, file.getBytes());
            file.transferTo(Paths.get(uploadConfig.getStorage(), saveFileName).toFile());
            // 返回文件URL
            // fileUrl=>http://localhost:8090/public/image/20210308091307174.png
            String fileUrl = uploadConfig.getHost() +uploadConfig.getUrlPrefix() + saveFileName;
            // 踩坑：关于返回的JSON字符串中 success 字段的值需要设置为 1 而不是 字符串 "1"
            //      若设置为 "1" 会导致图片url地址回显至前端页面失败
            result.put("success", 1);
            result.put("message", "上传成功！");
            result.put("url", fileUrl);
        } catch (Exception e) {
            result.put("success", 0);
            result.put("message", "上传失败！");
        }
        return result;
    }
}
