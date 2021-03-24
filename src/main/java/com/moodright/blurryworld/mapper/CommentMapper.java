package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.ChildComment;
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
     * 添加子评论
     * @param childComment  子评论实体类
     * @return 受影响的行数
     */
    int addChildComment(ChildComment childComment);

    /**
     * 根据文章编号分页查询评论
     * @param map key：startIndex: 查询起始索引,
     *                 pageSize: 分页数量,
     *                 postId: 文章编号
     */
    List<Comment> queryRootCommentsByPostId(Map<String,Integer> map);

    /**
     * 根据评论编号分页查询该评论下的子评论
     * @param map key: startIndex: 查询起始索引,
     *                 pageSize: 分页数量,
     *                 commentId: 评论编号
     */
    List<ChildComment> queryChildCommentsByCommentId(Map<String, Integer> map);

    /**
     * 根据文章编号查询根评论数量
     * @param postId 文章编号
     * @return 该篇文章下的根评论总数
     */
    int queryRootCommentCountsByPostId(@Param("postId")Integer postId);

    /**
     * 根据文章编号查询全部评论数量
     * @param postId 文章编号
     * @return 该篇文章下的评论总数
     */
    int queryCommentCountsByPostId(@Param("postId")Integer postId);

    /**
     * 根据评论编号伪删除根评论
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    int deleteRootCommentByCommentId(@Param("commentId")Integer commentId);

    /**
     * 根据评论编号伪删除子评论
     * @param commentId 评论编号
     * @return 受影响的行数
     */
    int deleteChildCommentByCommentId(@Param("commentId")Integer commentId);

    /**
     * 根据评论编号查询评论
     * @param commentId 评论编号
     * @return 评论对象
     */
    Comment queryCommentByCommentId(@Param("commentId")Integer commentId);


}
