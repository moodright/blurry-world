package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.pojo.createcenter.CreateCenterComment;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.utils.PostPaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author moodright
 * @date 2021/3/17
 */

@Controller
@RequestMapping("platform")
public class CreateCenterController {

    PostService postService;
    PostPaginationUtil postPaginationUtil;
    CommentService commentService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setPostPaginationUtil(PostPaginationUtil postPaginationUtil) {
        this.postPaginationUtil = postPaginationUtil;
        // 分页大小初始化
        this.postPaginationUtil.initialPageSize(5);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String createCenterIndex() {
        return "/user/post-management/create-center";
    }

    @GetMapping(path = {"post", "post/{pn}"})
    public String postManagementIndex(@PathVariable(name = "pn", required = false)Integer pageNumber, HttpSession session, Model model) {
        // 会话中获取当前用户信息
        User user = (User)session.getAttribute("user");
        // 更新分页信息
        postPaginationUtil.updatePaginationInfo(pageNumber, user.getUserId(), postService.queryPostsCountByAuthorId(user.getUserId()));
        // 分页查询
        List<Post> posts = postService.queryPostsByPagination(postPaginationUtil.getPaginationInfo());
        // 将分页信息和文章信息添加到会话中
        if(!posts.isEmpty()) {
            model.addAttribute("posts", posts);
        }
        model.addAttribute("paginationInfo", postPaginationUtil.getPaginationInfo());
        return "/user/create-center/post-management";
    }

    @GetMapping(path= "comment")
    public String commentManagementIndex(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        // 封装分页查询数据
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("startIndex", 0);
        map.put("pageSize", 5);
        // 封装评论信息
        List<CreateCenterComment> createCenterComments = commentService.queryCreateCenterCommentsByUserId(map);
        model.addAttribute("createCenterComments", createCenterComments);
        return "/user/create-center/comment-management";
    }



}
