package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.PostIndexMapper;
import com.moodright.blurryworld.mapper.PostMapper;
import com.moodright.blurryworld.pojo.PostIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @param postIndexMap 索引词图
     * @return 索引词列表
     */
    @Override
    public List<PostIndex> queryPostIndexByPostIndexMap(Map<String, Map<Integer, Integer>> postIndexMap) {
        return postIndexMapper.queryPostIndexByPostIndexMap(postIndexMap);
    }

    /**
     * 添加索引
     *
     * @param postIndexMap 索引词图
     * @return 受影响的行数
     */
    @Override
    public int addPostIndex(Map<String, String> postIndexMap) {
        return postIndexMapper.addPostIndex(postIndexMap);
    }

    /**
     * 更新索引
     *
     * @param postIndexMap 索引词图
     * @return 受影响的行数
     */
    @Override
    public int updatePostIndex(Map<String, String> postIndexMap) {
        return postIndexMapper.updatePostIndex(postIndexMap);
    }
}
