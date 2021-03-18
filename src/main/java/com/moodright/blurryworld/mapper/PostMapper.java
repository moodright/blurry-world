package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/14
 */

@Mapper
@Repository
public interface PostMapper {

    /**
     * 创建文章
     * @param post 文章对象
     * @return 受影响的行数
     */
    int addPost(Post post);

    /**
     * 根据文章编号更新文章
     * @param post 文章对象
     * @return 受影响的行数
     */
    int updatePostByPostId(Post post);

    /**
     * 根据文章编号查询文章
     * @param postId 文章编号
     * @return 文章对象
     */
    Post queryPostByPostId(@Param("postId")Integer postId);

    /**
     * 根据作者编号查询该作者的全部文章
     * @param authorId 作者编号
     * @return 文章对象列表
     */
    List<Post> queryAllPostsByUserId(@Param("authorId")Integer authorId);

    /**
     * 分页查询文章
     * @param paginationInfo 分页信息
     *                       startIndex : 起始下标
     *                       pageSize : 分页数量
     *                       authorId : 作者编号
     * @return 文章对象列表
     */
    List<Post> queryPostsByPagination(Map<String, Integer> paginationInfo);


    /**
     * 根据作者编号查询文章数量
     * @param authorId 作者编号
     * @return 文章数量
     */
    int queryPostsCountByAuthorId(@Param("authorId")Integer authorId);
}
