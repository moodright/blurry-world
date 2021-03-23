package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.CommentDTO;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.UserService;
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

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
     * @param commentParentId 父评论编号
     * @param commentContent 评论内容
     * @param parentCommentAuthorId 回复的评论作者编号
     */
    @PostMapping("reply")
    @ResponseBody
    public String replyComment(@RequestParam("postId")Integer postId,
                               @RequestParam("commentId")Integer commentParentId,
                               @RequestParam("commentContent")String commentContent,
                               @RequestParam("parentCommentAuthorId")Integer parentCommentAuthorId,
                               @RequestParam("parentCommentAuthorUsername")String parentCommentAuthorUsername,
                               HttpSession session) {
        // 封装子评论数据
        User user = (User)session.getAttribute("user");
        ChildComment childComment = new ChildComment();
        childComment.setCommentAuthorId(user.getUserId());
        childComment.setCommentPostId(postId);
        childComment.setCommentParentId(commentParentId);
        childComment.setCommentCreateTime(new Date());
        childComment.setCommentContent(commentContent);
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
     * @return CommentDTO 列表
     */
    @GetMapping("query")
    @ResponseBody
    public List<CommentDTO> queryComment(@RequestParam("postId")Integer postId,
                                         HttpSession session) {
        // 分页查询参数
        Map<String,Integer> map = new HashMap<>();
        map.put("postId", postId);
        map.put("startIndex", 0);
        map.put("pageSize", 10);
        // 分页查询该篇文章下的评论
        List<Comment> comments = commentService.queryCommentsByPostId(map);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            // 封装评论对象
            commentDTO.setCommentId(comment.getCommentId());
            commentDTO.setCommentAuthorId(comment.getCommentAuthorId());
            commentDTO.setCommentContent(comment.getCommentContent());
            commentDTO.setCommentCreateTime(comment.getCommentCreateTime());
            commentDTO.setCommentLikes(comment.getCommentLikes());
            commentDTO.setCommentDislikes(comment.getCommentDislikes());
            User user = userService.queryUserById(comment.getCommentAuthorId());
            commentDTO.setCommentAuthorUsername(user.getUsername());
            commentDTO.setCommentAuthorAvatar(user.getAvatar());
            // 封装回复对象
            User replyer = (User)session.getAttribute("user");
            commentDTO.setReplyerAvatar(replyer.getAvatar());
            // 封装该条评论下的子评论对象
            map.put("commentId", comment.getCommentId());
            // 根据父评论编号查询子评论
            List<ChildComment> childComments = commentService.queryChildCommentsByCommentId(map);
            for(ChildComment childComment : childComments) {
                User childCommentAuthor = userService.queryUserById(childComment.getCommentAuthorId());
                // 封装评论作者用户名
                childComment.setCommentAuthorUsername(childCommentAuthor.getUsername());
                // 封装评论作者头像
                childComment.setCommentAuthorAvatar(childCommentAuthor.getAvatar());
            }
            commentDTO.setChildComments(childComments);
            // 将封装好的CommentDTO添加进列表中
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }



}
