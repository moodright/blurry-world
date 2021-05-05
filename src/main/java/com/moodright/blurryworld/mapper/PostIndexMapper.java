package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.PostIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/2
 */
@Mapper
@Repository
public interface PostIndexMapper {

    /**
     * 根据索引名查询索引
     * @param postIndexMap 索引词图
     * @return 索引词列表
     */
    List<PostIndex> queryPostIndexByPostIndexMap(@Param("postIndexMap") Map<String, Map<Integer, Integer>> postIndexMap);

    /**
     * 添加索引
     * @param postIndexMap 索引词图
     * @return 受影响的行数
     */
    int addPostIndex(@Param("postIndexMap") Map<String, String> postIndexMap);

    /**
     * 更新索引
     * @param postIndexMap 索引词图
     * @return 受影响的行数
     */
    int updatePostIndex(@Param("postIndexMap") Map<String, String> postIndexMap);
}
