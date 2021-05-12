package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.PostMapper;
import com.moodright.blurryworld.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/14
 */

@Service
public class PostService implements PostMapper {

    PostMapper postMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    /**
     * 创建文章
     *
     * @param post 文章对象
     * @return 受影响的行数
     */
    @Override
    public int addPost(Post post) {
        return postMapper.addPost(post);
    }

    /**
     * 根据文章编号更新文章
     *
     * @param post 文章对象
     * @return 受影响的行数
     */
    @Override
    public int updatePostByPostId(Post post) {
        return postMapper.updatePostByPostId(post);
    }

    /**
     * 根据文章编号查询文章
     *
     * @param postId 文章编号
     * @return 文章对象
     */
    @Override
    public Post queryPostByPostId(Integer postId) {
        return postMapper.queryPostByPostId(postId);
    }

    /**
     * 根据作者编号查询该作者的全部文章
     *
     * @param authorId 作者编号
     * @return 文章对象列表
     */
    @Override
    public List<Post> queryAllPostsByUserId(Integer authorId) {
        return postMapper.queryAllPostsByUserId(authorId);
    }

    /**
     * 分页查询文章
     *
     * @param paginationInfo 分页信息
     *                       startIndex : 起始下标
     *                       pageSize : 分页数量
     *                       authorId : 作者编号
     * @return 文章对象列表
     */
    @Override
    public List<Post> queryPostsByPagination(Map<String, Integer> paginationInfo) {
        return postMapper.queryPostsByPagination(paginationInfo);
    }

    /**
     * 根据作者编号查询文章数量
     *
     * @param authorId 作者编号
     * @return 文章数量
     */
    @Override
    public int queryPostsCountByAuthorId(Integer authorId) {
        return postMapper.queryPostsCountByAuthorId(authorId);
    }

    /**
     * 根据文章编号查询作者编号
     *
     * @param postId 文章编号
     * @return 作者编号
     */
    @Override
    public Integer queryAuthorIdByPostId(Integer postId) {
        return postMapper.queryAuthorIdByPostId(postId);
    }

    /**
     * 根据输入字符串模糊查询文章名
     *
     * @param inputString 输入字符串
     * @return 推荐文章名List
     */
    @Override
    public List<String> suggestPostTitleByInputString(String inputString) {
        return postMapper.suggestPostTitleByInputString(inputString);
    }
}
