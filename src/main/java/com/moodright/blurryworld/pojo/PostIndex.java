package com.moodright.blurryworld.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/2
 */
@Data
public class PostIndex {
    /**
     * 索引词名字
     */
    private String indexName;
    /**
     * 索引图：json字符串形式存储
     * {"文章编号":该索引词在文章中出现的频率, ... }
     */
    private String indexMap;
}
