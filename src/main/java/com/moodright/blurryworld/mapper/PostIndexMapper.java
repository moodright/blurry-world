package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.PostIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
     * @param indexName 索引名
     * @return 索引实体类
     */
    PostIndex queryPostIndexByIndexName(@Param("indexName") String indexName);

    /**
     * 添加索引
     * @param postIndex 索引实体类
     * @return 受影响的行数
     */
    int addPostIndex(PostIndex postIndex);

    /**
     * 更新索引
     * @param postIndex 索引实体类
     * @return 受影响的行数
     */
    int updatePostIndex(PostIndex postIndex);
}
