package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.TagMapper;
import com.moodright.blurryworld.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/5
 */
@Service
public class TagService implements TagMapper {
    private TagMapper tagMapper;

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    /**
     * 添加标签
     *
     * @param tagList 标签列表
     * @return 受影响的行数
     */
    @Override
    public int addTags(List<Tag> tagList) {
        return tagMapper.addTags(tagList);
    }

    /**
     * 根据文章编号查询标签
     *
     * @param postId 文章编号
     * @return 标签列表
     */
    @Override
    public List<Tag> queryTagsByPostId(Integer postId) {
        return tagMapper.queryTagsByPostId(postId);
    }
}
