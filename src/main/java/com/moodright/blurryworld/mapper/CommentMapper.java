package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/20
 */

@Mapper
@Repository
public interface CommentMapper {
    /**
     * 添加评论
     * @param comment 评论实体类
     * @return 受影响的行数
     */
    int addComment(Comment comment);

    /**
     * 根据文章编号分页查询评论
     * @param map key：startIndex: 查询起始索引,
     *                 pageSize: 分页数量,
     *                 postId: 文章编号
     */
    List<Comment> queryCommentsByPostId(Map<String,Integer> map);

    /**
     * 根据评论编号分页查询该评论下的子评论
     * @param map key: startIndex: 查询起始索引,
     *                 pageSize: 分页数量,
     *                 commentId: 评论编号
     */
    List<Comment> queryChildCommentsByCommentId(Map<String, Integer> map);

    /**
     * 根据评论编号伪删除评论
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    int deleteCommentsByCommentId(@Param("commentId")Integer commentId);


}
