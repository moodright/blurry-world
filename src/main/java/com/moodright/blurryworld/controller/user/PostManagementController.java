package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.Draft;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.DraftService;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 文章管理控制器
 * @author moodright
 * @date 2021/3/7
 */
@Controller
@RequestMapping("post")
public class PostManagementController {

    PostService postService;
    UserService userService;
    DraftService draftService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setDraftService(DraftService draftService) {
        this.draftService = draftService;
    }

    /**
     * 编写文章
     * @return 编辑文章页面模板
     */
    @GetMapping
    public String editPost(HttpSession session,
                           Model model) {
        // 首次编辑文章为用户创建草稿
        User user = (User)session.getAttribute("user");
        Draft draft = draftService.queryDraftByAuthorId(user.getUserId());
        if(draft == null) {
            draft = new Draft();
            draft.setAuthorId(user.getUserId());
            draft.setDraftTitle("");
            draft.setDraftContent("");
            draftService.addDraft(draft);
            model.addAttribute("draft", draft);
        } else {
            model.addAttribute("draft", draft);
        }
        return "/user/post-management/edit-post";
    }

    /**
     * 保存草稿
     * @param draftTitle 草稿名
     * @param draftContent 草稿内容
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public String saveDraft(@RequestParam("post-title")String draftTitle,
                            @RequestParam("post-content")String draftContent,
                            HttpSession session) {
        // 封装草稿信息
        User user = (User)session.getAttribute("user");
        Draft draft = new Draft();
        draft.setAuthorId(user.getUserId());
        draft.setDraftTitle(draftTitle);
        draft.setDraftContent(draftContent);
        // 更新草稿信息
        if(draftService.updateDraftByAuthorId(draft) > 0) {
            return "1";
        }
        return "0";
    }

    /**
     * 上传文章
     * @param title 文章标题
     * @param content 文章内容
     * @return 显示文章Mapping
     */
    @PostMapping("write")
    public String writePost(@RequestParam("post-title")String title,
                            @RequestParam("editormd-markdown-doc")String content,
                            @RequestParam("postReleaseTime")String releaseTime,
                            @RequestParam("postType")Integer type,
                            @RequestParam("tags")String tags,
                            @RequestParam("description")String description,
                            @RequestParam("cover")MultipartFile cover,
                            HttpSession session) {
        // 封装文章数据
        User user = (User)session.getAttribute("user");
        Post post = new Post();
        post.setAuthorId(user.getUserId());
        post.setPostTitle(title);
        post.setPostContent(content);
        post.setPostType(type);
        if(!description.equals("")) {
            post.setPostDescription(description);
        }else {
            // 文章简介为空自动截取
            if(content.length() > 50) {
                post.setPostDescription(content.substring(0, 50));
            }else {
                post.setPostDescription(content.substring(0, content.length() / 2));
            }
        }
        if(!releaseTime.equals("")) {
            post.setPostReleaseTime(DateUtil.releaseTimeStringToDate(releaseTime));
        }else {
            post.setPostReleaseTime(new Date());
        }
        post.setPostCreateTime(new Date());
        post.setPostUpdateTime(new Date());

        // 封装标签
//        String[] split = tags.split(";");
//        for (int i = 0; i < split.length ; i++) {
//            System.out.println(split[i]);
//        }

        // 封装文章封面图片
//        System.out.println("cover=>" + cover);


        // 添加文章
        postService.addPost(post);
        // 删除此篇文章的草稿
        draftService.deleteDraftByAuthorID(user.getUserId());
        return "redirect:/main";
    }

    /**
     * 浏览文章
     * @param id 文章编号
     * @return 显示文章模板
     */
    @GetMapping("read/{postId}")
    public String displayPost(@PathVariable("postId")Integer id,
                              Model model) {
        Post post = postService.queryPostByPostId(id);
        User author = userService.queryUserById(post.getAuthorId());
        model.addAttribute("post", post);
        model.addAttribute("author", author);
        return "/user/post-management/display-post";
    }

}
