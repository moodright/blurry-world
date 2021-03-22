package com.moodright.blurryworld;

import com.moodright.blurryworld.mapper.CommentMapper;
import com.moodright.blurryworld.pojo.Comment;
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
        map.put("postId", 2000);
        map.put("startIndex", 0);
        map.put("pageSize", 2);
        List<Comment> comments = commentMapper.queryCommentsByPostId(map);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void deleteCommentsByCommentIdTest() {
        commentMapper.deleteCommentsByCommentId(1);
    }

    @Test
    public void queryChildCommentByComentIdTest() {
        Map<String,Integer> map = new HashMap<>();
        map.put("commentId", 6);
        map.put("startIndex", 0);
        map.put("pageSize", 10);
        List<Comment> comments = commentMapper.queryChildCommentsByCommentId(map);
        for(Comment comment : comments) {
            System.out.println(comment);
        }
    }
}
