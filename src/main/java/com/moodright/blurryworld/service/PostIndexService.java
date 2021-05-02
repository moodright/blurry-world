package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.PostIndexMapper;
import com.moodright.blurryworld.mapper.PostMapper;
import com.moodright.blurryworld.pojo.PostIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moodright
 * @date 2021/5/2
 */
@Service
public class PostIndexService implements PostIndexMapper {

    private PostIndexMapper postIndexMapper;

    @Autowired
    public void setPostIndexMapper(PostIndexMapper postIndexMapper) {
        this.postIndexMapper = postIndexMapper;
    }

    /**
     * 根据索引名查询索引
     *
     * @param indexName 索引名
     * @return 索引实体类
     */
    @Override
    public PostIndex queryPostIndexByIndexName(String indexName) {
        return postIndexMapper.queryPostIndexByIndexName(indexName);
    }

    /**
     * 添加索引
     *
     * @param postIndex 索引实体类
     * @return 受影响的行数
     */
    @Override
    public int addPostIndex(PostIndex postIndex) {
        return postIndexMapper.addPostIndex(postIndex);
    }

    /**
     * 更新索引
     *
     * @param postIndex 索引实体类
     * @return 受影响的行数
     */
    @Override
    public int updatePostIndex(PostIndex postIndex) {
        return postIndexMapper.updatePostIndex(postIndex);
    }
}
