package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.config.CoverUploadConfig;
import com.moodright.blurryworld.pojo.Draft;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.Tag;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.DraftService;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.service.TagService;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
    TagService tagService;
    CoverUploadConfig coverUploadConfig;

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
    @Autowired
    public void setCoverUploadConfig(CoverUploadConfig coverUploadConfig) {
        this.coverUploadConfig = coverUploadConfig;
    }
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
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
     * @return 保存成功校验字段
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
                            HttpSession session) throws IOException {
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

        // 封装文章封面图片
        if(!cover.isEmpty()) {
            String saveFileName = user.getUserId() + "" + new Date().getTime() + ".png";
            Path path = Paths.get(coverUploadConfig.getStorage(), saveFileName);
            Files.write(path, cover.getBytes());
            cover.transferTo(Paths.get(coverUploadConfig.getStorage(), saveFileName).toFile());
            // 封面url
            String coverUrl = coverUploadConfig.getHost() + coverUploadConfig.getUrlPrefix() + saveFileName;
            post.setPostCover(coverUrl);
        }

        // 添加文章
        postService.addPost(post);
        // 封装标签
        if (tags.length() != 0) {
            List<Tag> tagList = new ArrayList<>();
            String[] split = tags.split(";");
            for (int i = 0; i < split.length ; i++) {
                tagList.add(new Tag(post.getPostId(), split[i]));
            }
            // 添加标签
            tagService.addTags(tagList);
        }
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
        List<Tag> tagList = tagService.queryTagsByPostId(id);
        model.addAttribute("post", post);
        model.addAttribute("author", author);
        model.addAttribute("tagList", tagList);
        return "/user/post-management/display-post";
    }

    /**
     * 文章更新页面
     * @param postId 文章编号
     * @return 文章更新模板
     */
    @GetMapping("update/{postId}")
    public String updatePost(@PathVariable("postId") Integer postId,
                             HttpSession session,
                             Model model) {
        User user = (User)session.getAttribute("user");
        Post post = postService.queryPostByPostId(postId);
        List<Tag> tagList = tagService.queryTagsByPostId(postId);
        // 访问不存在和文章和他人的文章异常情况
        if(post == null || !post.getAuthorId().equals(user.getUserId())) {
            return "/error/404";
        }
        model.addAttribute("post", post);
        model.addAttribute("tagList", tagList);
        return "/user/post-management/update-post";
    }

    /**
     * 更新文章
     * @param postId 文章编号
     * @param title 文章标题
     * @param content 文章内容
     * @param releaseTime 文章发布时间
     * @param type 文章类型
     * @param description 文章描述
     * @param cover 文章封面
     */
    @PostMapping("update/{postId}")
    public String updatePost(@PathVariable("postId") Integer postId,
                             @RequestParam("post-title")String title,
                             @RequestParam("editormd-markdown-doc")String content,
                             @RequestParam("postReleaseTime")String releaseTime,
                             @RequestParam("postType")Integer type,
//                             @RequestParam("tags")String tags,
                             @RequestParam("description")String description,
                             @RequestParam("cover")MultipartFile cover,
                             HttpSession session) throws IOException {
        User user = (User)session.getAttribute("user");
        // 封装文章数据
        Post post = new Post();
        post.setPostId(postId);
        post.setPostType(type);
        post.setPostTitle(title);
        post.setPostUpdateTime(new Date());
        post.setPostDescription(description);
        post.setPostContent(content);
        post.setPostReleaseTime(DateUtil.releaseTimeStringToDate(releaseTime));
        if(!cover.isEmpty()) {
            String saveFileName = user.getUserId() + "-" + new Date().getTime() + ".png";
            Path path = Paths.get(coverUploadConfig.getStorage(), saveFileName);
            Files.write(path, cover.getBytes());
            cover.transferTo(Paths.get(coverUploadConfig.getStorage(), saveFileName).toFile());
            String coverUrl = coverUploadConfig.getHost() + coverUploadConfig.getUrlPrefix() + saveFileName;
            post.setPostCover(coverUrl);
        }
        postService.updatePostByPostId(post);
        return "redirect:/platform/post";
    }

    /**
     * 删除文章
     * @param postId 文章编号
     */
    @PostMapping("delete/{postId}")
    @ResponseBody
    public String deletePost(@PathVariable("postId")Integer postId,
                             HttpSession session) {
        User user = (User)session.getAttribute("user");
        Post post = postService.queryPostByPostId(postId);
        // 判断文章不存在或此文章不属于此作者时删除失败
        if (post == null || !post.getAuthorId().equals(user.getUserId())) {
            return "false";
        }
        post = new Post();
        post.setPostId(postId);
        post.setPostStatus(true);
        postService.updatePostByPostId(post);
        return "success";
    }

}
