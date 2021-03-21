package com.moodright.blurryworld.controller.user;

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
     * @param session 当前会话
     * @return
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
        commentService.addComment(comment);
        return "success";
    }

    @GetMapping("query")
    @ResponseBody
    public List<CommentDTO> queryComment(@RequestParam("postId")Integer postId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("postId", postId);
        map.put("startIndex", 0);
        map.put("pageSize", 10);
        // 分页查询该篇文章下的评论
        List<Comment> comments = commentService.queryCommentsByPostId(map);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment : comments) {
            // 封装CommentDTO
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentId(comment.getCommentId());
            commentDTO.setCommentAuthorId(comment.getCommentAuthorId());
            commentDTO.setCommentContent(comment.getCommentContent());
            commentDTO.setCommentCreateTime(comment.getCommentCreateTime());
            commentDTO.setCommentLikes(comment.getCommentLikes());
            commentDTO.setCommentDislikes(comment.getCommentDislikes());
            User user = userService.queryUserById(comment.getCommentAuthorId());
            commentDTO.setUsername(user.getUsername());
            commentDTO.setAvatar(user.getAvatar());
            // 将封装好的CommentDTO添加进列表中
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }



}
