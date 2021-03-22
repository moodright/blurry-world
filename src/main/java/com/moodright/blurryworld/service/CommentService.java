package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.CommentMapper;
import com.moodright.blurryworld.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/20
 */
@Service
public class CommentService implements CommentMapper {

    CommentMapper commentMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 添加评论
     *
     * @param comment 评论实体类
     * @return 受影响的行数
     */
    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    /**
     * 根据文章编号分页查询评论
     *
     * @param map key：startIndex: 查询起始索引,
     *            pageSize: 分页数量,
     *            postId: 文章编号
     */
    @Override
    public List<Comment> queryCommentsByPostId(Map<String, Integer> map) {
        return commentMapper.queryCommentsByPostId(map);
    }

    /**
     * 根据评论编号分页查询该评论下的子评论
     *
     * @param map key: startIndex: 查询起始索引,
     *            pageSize: 分页数量,
     *            commentId: 评论编号
     */
    @Override
    public List<Comment> queryChildCommentsByCommentId(Map<String, Integer> map) {
        return commentMapper.queryChildCommentsByCommentId(map);
    }

    /**
     * 根据评论编号伪删除评论
     *
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    @Override
    public int deleteCommentsByCommentId(Integer commentId) {
        return commentMapper.deleteCommentsByCommentId(commentId);
    }
}
