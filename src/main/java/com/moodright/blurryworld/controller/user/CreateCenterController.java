package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.pojo.createcenter.CreateCenterComment;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.utils.PostPaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String postManagementIndex(@PathVariable(name = "pn", required = false)Integer pageNumber,
                                      HttpSession session,
                                      Model model) {
        // 空值初始化
        if(pageNumber == null) {
            pageNumber = 1;
        }
        // 会话中获取当前用户信息
        User user = (User)session.getAttribute("user");
        // 更新分页信息
        postPaginationUtil.updatePaginationInfo(pageNumber, user.getUserId(), postService.queryPostsCountByAuthorId(user.getUserId()));
        // 错误页面跳转
        if(pageNumber > postPaginationUtil.getPaginationInfo().get("totalPagesCount") && postPaginationUtil.getPaginationInfo().get("totalPagesCount") != 0  || pageNumber <= 0) {
            return "/error/404";
        }
        // 分页查询
        List<Post> posts = postService.queryPostsByPagination(postPaginationUtil.getPaginationInfo());
        // 将分页信息和文章信息添加到会话中
        if(!posts.isEmpty()) {
            model.addAttribute("posts", posts);
        }
        model.addAttribute("paginationInfo", postPaginationUtil.getPaginationInfo());
        return "/user/create-center/post-management";
    }

    @GetMapping(path= {"comment", "comment/{pn}"})
    public String commentManagementIndex(HttpSession session,
                                         @PathVariable(value = "pn", required = false)Integer pageNumber,
                                         Model model) {
        // 空值初始化
        if(pageNumber == null) {
            pageNumber = 1;
        }
        User user = (User)session.getAttribute("user");
        // 更新分页信息
        postPaginationUtil.updateCreateCenterCommentPaginationInfo(pageNumber, user.getUserId(), commentService.queryCreateCenterCommentCountByUserId(user.getUserId()));
        // 错误页面跳转
        if(pageNumber > postPaginationUtil.getPaginationInfo().get("totalPagesCount") && postPaginationUtil.getPaginationInfo().get("totalPagesCount") != 0 || pageNumber <= 0) {
            return "/error/404";
        }
        // 封装评论信息
        List<CreateCenterComment> createCenterComments = commentService.queryCreateCenterCommentsByUserId(postPaginationUtil.getPaginationInfo());
        model.addAttribute("createCenterComments", createCenterComments);
        model.addAttribute("paginationInfo", postPaginationUtil.getPaginationInfo());
        return "/user/create-center/comment-management";
    }

    /**
     * 根据评论编号查询评论
     * @param commentId 评论编号
     */
    @GetMapping(path = "/comment/query/{id}")
    @ResponseBody
    public CreateCenterComment queryCommentById(@PathVariable("id")Integer commentId) {
        return commentService.queryCreateCenterCommentByCommentId(commentId);
    }

    /**
     * 回复评论
     *
     * 和 {@link com.moodright.blurryworld.controller.user.CommentController#replyComment} 获取的参数其实是相同的
     * 这里没有获取多余的两个用户参数（用户编号和用户名）以及根评论编号
     * 上述三个参数都可以根据 parentCommentId 进行联表查询和判断根评论编号
     * 是否为空得出
     *
     * @param postId 回复的文章编号
     * @param parentCommentId 回复的评论编号
     * @param commentContent 回复的内容
     * @param session 当前会话, 用来读取当前用户的个人信息
     * @return 判断回复是否成功的字段
     */
    @PostMapping("comment/reply")
    @ResponseBody
    public String replyComment(@RequestParam("postId")Integer postId,
                              @RequestParam("commentId")Integer parentCommentId,
                              @RequestParam("commentContent")String commentContent,
                              HttpSession session) {
        User user = (User)session.getAttribute("user");
        // 父评论、评论人信息
        CreateCenterComment createCenterComment = commentService.queryCreateCenterCommentByCommentId(parentCommentId);
        // 封装子评论数据
        ChildComment childComment = new ChildComment();
        childComment.setCommentPostId(postId);
        childComment.setCommentAuthorId(user.getUserId());
        childComment.setCommentContent(commentContent);
        childComment.setCommentCreateTime(new Date());
        // 回复的评论编号
        childComment.setParentCommentId(parentCommentId);
        childComment.setParentCommentAuthorId(createCenterComment.getUser().getUserId());
        childComment.setParentCommentAuthorUsername(createCenterComment.getUser().getUsername());
        // 设置根评论编号
        if(createCenterComment.getComment().getCommentParentId() == null) {
            // 父评论的根评论编号为空则为根评论
            childComment.setCommentParentId(createCenterComment.getComment().getCommentId());
        }else {
            childComment.setCommentParentId(createCenterComment.getComment().getCommentParentId());
        }
        int i = commentService.addChildComment(childComment);
        if(i > 0) {
            return "success";
        }else {
            return "failed";
        }
    }


    @PostMapping("comment/delete/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id")Integer commentId) {
        System.out.println("commentId=>" + commentId);
        int i = commentService.deleteRootCommentByCommentId(commentId);
        if (i > 0) {
            return "success";
        }else {
            return "failed";
        }
    }
}
