package com.moodright.blurryworld;

import com.moodright.blurryworld.mapper.CommentMapper;
import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.createcenter.CreateCenterComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/20
 */
@SpringBootTest
public class CommentMapperTests {

    @Autowired
    CommentMapper commentMapper;

    @Test
    public void addCommentTest() {
        Comment comment = new Comment();
        comment.setCommentPostId(2000);
        comment.setCommentAuthorId(5555);
        comment.setCommentCreateTime(new Date());
        comment.setCommentContent("ssssss");
        comment.setCommentParentId(5555);
        int i = commentMapper.addComment(comment);
        System.out.println(i);
    }

    @Test
    public void queryCommentsByPostIdTest() {
        Map<String,Integer> map = new HashMap<>();
        map.put("postId", 2011);
        map.put("startIndex", 0);
        map.put("pageSize", 5);
        List<Comment> comments = commentMapper.queryRootCommentsByPostId(map);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void deleteCommentsByCommentIdTest() {
        commentMapper.deleteRootCommentByCommentId(1);
    }

    @Test
    public void queryChildCommentByComentIdTest() {
        Map<String,Integer> map = new HashMap<>();
        map.put("commentId", 119);
        map.put("startIndex", 0);
        map.put("pageSize", 10);
        List<ChildComment> comments = commentMapper.queryChildCommentsByCommentId(map);
        for(Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void queryCommentCountsByPostId() {
        int i = commentMapper.queryCommentCountsByPostId(2011);
        System.out.println(i);
    }


    @Test
    public void queryCreateCenterCommentsByUserIdTest() {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId", 1001);
        map.put("startIndex", 0);
        map.put("pageSize", 90);
        List<CreateCenterComment> createCenterComments = commentMapper.queryCreateCenterCommentsByUserId(map);
        for(CreateCenterComment createCenterComment : createCenterComments) {
            System.out.println(createCenterComment.getComment().getCommentContent());
            System.out.println(createCenterComment.getComment().getCommentId());
            System.out.println(createCenterComment);
        }
    }

    @Test
    public void queryCreateCenterCommentCountByUserIdTest() {
        int i = commentMapper.queryCreateCenterCommentCountByUserId(1001);
        System.out.println(i);
    }

    @Test
    public void queryCreateCenterCommentByCommentIdTest() {
        CreateCenterComment createCenterComment = commentMapper.queryCreateCenterCommentByCommentId(119);
        System.out.println(createCenterComment.getComment().getCommentId());
        System.out.println(createCenterComment.getComment().getCommentParentId());
        System.out.println(createCenterComment);
    }
}
