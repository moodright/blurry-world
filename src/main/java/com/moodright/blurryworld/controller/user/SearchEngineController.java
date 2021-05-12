package com.moodright.blurryworld.controller.user;

import com.alibaba.fastjson.JSON;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.ChineseSegmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author moodright
 * @date 2021/5/8
 */
@Controller
@RequestMapping("search")
public class SearchEngineController {

    ChineseSegmentUtil chineseSegmentUtil;
    UserService userService;
    PostService postService;

    @Autowired
    public void setChineseSegmentUtil(ChineseSegmentUtil chineseSegmentUtil) {
        this.chineseSegmentUtil = chineseSegmentUtil;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * 搜索主页面
     * @return 搜索主页面模板
     */
    @GetMapping
    public String searchTemplateIndex() {
        return "/user/search-engine/search-index";
    }

    /**
     * 返回推荐关键字Mapping
     * @param inputString 输入的字符串
     * @param searchType 输入的类型
     *                    0: 综合, 1: 用户; 2: 文章
     * @return
     */
    @PostMapping("suggestKeyWord")
    @ResponseBody
    public String suggestKeyWord(@RequestParam("inputString") String inputString, @RequestParam("searchType") Integer searchType) {
        // 输入字符串清理
        String cleaningInputString = chineseSegmentUtil.originalPostContentCleaning(inputString);
        // 综合
        if(searchType == 0) {
            return "failed";
        }else if(searchType == 1) {
            // 搜索用户
            if(!cleaningInputString.isEmpty()) {
                List<String> suggestString = userService.suggestUsernameByInputString(cleaningInputString);
                return JSON.toJSONString(suggestString);
            }
            return "failed";
        }else {
            // 搜索文章名
            if(!cleaningInputString.isEmpty()) {
                List<String> suggestString = postService.suggestPostTitleByInputString(cleaningInputString);
                return JSON.toJSONString(suggestString);
            }
            return "failed";
        }

    }




    /**
     * 搜索文章列表
     * @return 搜索文章页面模板
     */
    @GetMapping("post")
    public String searchPostIndex() {
        return "/user/search-engine/search-post";
    }

    /**
     * 搜索用户列表
     * @return 搜索用户页面模板
     */
    @GetMapping("user")
    public String searchUserIndex() {
        return "/user/search-engine/search-user";
    }

}
