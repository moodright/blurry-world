package com.moodright.blurryworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moodright
 * @date 2021/5/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    /**
     * 标签所在的文章编号
     */
    private Integer tagPostId;
    /**
     * 标签名
     */
    private String tagName;
}
