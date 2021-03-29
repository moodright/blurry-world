package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.CommentDTO;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.PostPaginationUtil;
import org.attoparser.IDocumentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author moodright
 * @date 2021/3/20
 */
@Controller
@RequestMapping(path = "comment")
public class CommentController {

    CommentService commentService;
    UserService userService;
    PostPaginationUtil postPaginationUtil;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPostPaginationUtil(PostPaginationUtil postPaginationUtil) {
        this.postPaginationUtil = postPaginationUtil;
        // 分页大小初始化
        this.postPaginationUtil.initialPageSize(5);
    }

    /**
     * 发表评论
     * @param postId 文章编号
     * @param commentContent 评论内容
     */
    @PostMapping("send")
    @ResponseBody
    public String sendComment(@RequestParam("postId")Integer postId,
                              @RequestParam("commentContent")String commentContent,
                              HttpSession session) {
        // 封装评论数据
        User user = (User)session.getAttribute("user");
        Comment comment = new Comment();
        comment.setCommentAuthorId(user.getUserId());
        comment.setCommentPostId(postId);
        comment.setCommentContent(commentContent);
        comment.setCommentCreateTime(new Date());
        int i = commentService.addComment(comment);
        if(i > 0) {
            return "success";
        }else {
            return "failed";
        }
    }


    /**
     * 回复评论
     * @param postId 文章编号
     * @param rootCommentId 根评论编号
     * @param commentContent 评论内容
     * @param parentCommentId 回复的评论编号
     * @param parentCommentAuthorId 回复的评论作者编号
     * @param parentCommentAuthorUsername 回复的评论作者用户名
     */
    @PostMapping("reply")
    @ResponseBody
    public String replyComment(@RequestParam("postId")Integer postId,
                               @RequestParam("commentId")Integer rootCommentId,
                               @RequestParam("commentContent")String commentContent,
                               @RequestParam("parentCommentId")Integer parentCommentId,
                               @RequestParam("parentCommentAuthorId")Integer parentCommentAuthorId,
                               @RequestParam("parentCommentAuthorUsername")String parentCommentAuthorUsername,
                               HttpSession session) {
        // 封装子评论数据
        User user = (User)session.getAttribute("user");
        ChildComment childComment = new ChildComment();
        childComment.setCommentAuthorId(user.getUserId());
        childComment.setCommentPostId(postId);
        childComment.setCommentParentId(rootCommentId);
        childComment.setCommentCreateTime(new Date());
        childComment.setCommentContent(commentContent);
        childComment.setParentCommentId(parentCommentId);
        // 封装回复的作者编号用户名
        childComment.setParentCommentAuthorId(parentCommentAuthorId);
        childComment.setParentCommentAuthorUsername(parentCommentAuthorUsername);
        int i = commentService.addChildComment(childComment);
        if(i > 0) {
            return "success";
        }
        else {
            return "failed";
        }
    }



    /**
     * 根据文章编号查询评论
     * @param postId 文章编号
     * @return CommentDTO
     */
    @GetMapping("query")
    @ResponseBody
    public CommentDTO queryComment(@RequestParam("postId")int postId,
                                   @RequestParam("pn")int pageNumber,
                                   @RequestParam("orderType")String orderType,
                                   HttpSession session) {
        // 更新分页信息
        postPaginationUtil.updateCommentPaginationInfo(pageNumber, postId, commentService.queryRootCommentCountsByPostId(postId), commentService.queryCommentCountsByPostId(postId));
        // 校验排序字段
        if(orderType.equals("time")) {
            postPaginationUtil.getPaginationInfo().put("orderByTime", 1);
        } else {
            postPaginationUtil.getPaginationInfo().put("orderByTime", null);
        }
        // 分页查询该篇文章下的根评论
        List<Comment> comments = commentService.queryRootCommentsByPostId(postPaginationUtil.getPaginationInfo());
        CommentDTO commentDTO = new CommentDTO();
        for (int i = 0; i < comments.size(); i++) {
            // 封装回复对象
            User replyer = (User)session.getAttribute("user");
            commentDTO.setReplyerAvatar(replyer.getAvatar());
            commentDTO.setReplyerId(replyer.getUserId());
            // 封装根评论对象
            User user = userService.queryUserById(comments.get(i).getCommentAuthorId());
            comments.get(i).setCommentAuthorAvatar(user.getAvatar());
            comments.get(i).setCommentAuthorUsername(user.getUsername());
            commentDTO.getRootComment().put("" + i, comments.get(i));
            // 子评论分页查询参数（待完善）
            Map<String,Integer> map = new HashMap<>();
            map.put("commentId", comments.get(i).getCommentId());
            map.put("startIndex", 0);
            map.put("pageSize", 10);
            // 根据父评论编号查询子评论
            List<ChildComment> childComments = commentService.queryChildCommentsByCommentId(map);
            for (int j = 0; j < childComments.size() ; j++) {
                User childCommentAuthor = userService.queryUserById(childComments.get(j).getCommentAuthorId());
                // 封装评论作者用户名
                childComments.get(j).setCommentAuthorUsername(childCommentAuthor.getUsername());
                // 封装评论作者头像
                childComments.get(j).setCommentAuthorAvatar(childCommentAuthor.getAvatar());
                // 封装回复的评论编号
                childComments.get(j).setParentCommentId(childComments.get(j).getCommentId());
            }
            // 封装子评论对象
            commentDTO.getChildComments().put("" + i, childComments);
        }
        // 封装分页信息
        commentDTO.setPaginationInfo(postPaginationUtil.getPaginationInfo());
        return commentDTO;
    }

    /**
     * 根据评论编号删除评论
     * @param commentId 评论编号
     * @return 校验字符串
     */
    @PostMapping("delete")
    @ResponseBody
    public String deleteCommentById(@RequestParam("commentId")Integer commentId) {
        Comment comment = commentService.queryCommentByCommentId(commentId);
        int i;
        // 判断该评论是根评论还是子评论
        if(comment.getCommentParentId() != null) {
            i = commentService.deleteChildCommentByCommentId(commentId);
        }else {
            i = commentService.deleteRootCommentByCommentId(commentId);
        }
        if(i > 0) {
            return "success";
        }else {
            return "failed";
        }
    }

}
