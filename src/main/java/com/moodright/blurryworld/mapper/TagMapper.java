package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/5
 */
@Mapper
@Repository
public interface TagMapper {

    /**
     * 添加标签
     * @param tagList 标签list
     * @return 受影响的行数
     */
    int addTags(@Param("tagList") List<Tag> tagList);

    /**
     * 根据文章编号查询标签
     * @param postId 文章编号
     * @return 标签列表
     */
    List<Tag> queryTagsByPostId(@Param("postId") Integer postId);
}
