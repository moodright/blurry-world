package com.moodright.blurryworld.controller.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/7
 */
@Controller
@RequestMapping("picture")
public class PictureUploadController {


//    @PostMapping("upload")
//    @ResponseBody
//    public Map<Object, String> pictureUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
//        String trueFileName = file.getOriginalFilename();
//        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
//        String fileName = System.currentTimeMillis() + "_" + suffix;
//        System.out.println("trueFileName=>" + trueFileName);
//        System.out.println("fileName=>" + fileName);
//        System.out.println("path=>" + request.getSession().getServletContext().getRealPath("/assests/msg/upload"));
//        System.out.println("webRoot=>" + );
//        HashMap<Object, String> map = new HashMap<>();
//        map.put("url", "11");
//        map.put("success", "1");
//        map.put("message", "upload success!");
//        return map;
//
//    }

}
