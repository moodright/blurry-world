package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.CommentMapper;
import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.createcenter.CreateCenterComment;
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
     * 添加子评论
     *
     * @param childComment 子评论实体类
     * @return 受影响的行数
     */
    @Override
    public int addChildComment(ChildComment childComment) {
        return commentMapper.addChildComment(childComment);
    }

    /**
     * 根据文章编号分页查询评论
     *
     * @param map key：startIndex: 查询起始索引,
     *            pageSize: 分页数量,
     *            postId: 文章编号
     */
    @Override
    public List<Comment> queryRootCommentsByPostId(Map<String, Integer> map) {
        return commentMapper.queryRootCommentsByPostId(map);
    }

    /**
     * 根据评论编号分页查询该评论下的子评论
     *
     * @param map key: startIndex: 查询起始索引,
     *            pageSize: 分页数量,
     *            commentId: 评论编号
     */
    @Override
    public List<ChildComment> queryChildCommentsByCommentId(Map<String, Integer> map) {
        return commentMapper.queryChildCommentsByCommentId(map);
    }

    /**
     * 根据文章编号查询根评论数量
     *
     * @param postId 文章编号
     * @return 该篇文章下的根评论总数
     */
    @Override
    public int queryRootCommentCountsByPostId(Integer postId) {
        return commentMapper.queryRootCommentCountsByPostId(postId);
    }

    /**
     * 根据文章编号查询全部评论数量
     *
     * @param postId 文章编号
     * @return 该篇文章下的评论总数
     */
    @Override
    public int queryCommentCountsByPostId(Integer postId) {
        return commentMapper.queryCommentCountsByPostId(postId);
    }

    /**
     * 根据评论编号伪删除评论
     *
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    @Override
    public int deleteRootCommentByCommentId(Integer commentId) {
        return commentMapper.deleteRootCommentByCommentId(commentId);
    }

    /**
     * 根据评论编号伪删除子评论
     *
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    @Override
    public int deleteChildCommentByCommentId(Integer commentId) {
        return commentMapper.deleteChildCommentByCommentId(commentId);
    }

    /**
     * 根据评论编号查询评论
     *
     * @param commentId 评论编号
     * @return 评论对象
     */
    @Override
    public Comment queryCommentByCommentId(Integer commentId) {
        return commentMapper.queryCommentByCommentId(commentId);
    }

    /**
     * 根据用户编号查询创作中心评论信息
     *
     * @param map key:userId, startIndex, pageSize
     * @return
     */
    @Override
    public List<CreateCenterComment> queryCreateCenterCommentsByUserId(Map<String,Integer> map) {
        return commentMapper.queryCreateCenterCommentsByUserId(map);
    }
}
