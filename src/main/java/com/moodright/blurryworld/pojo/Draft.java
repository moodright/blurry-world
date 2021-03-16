package com.moodright.blurryworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moodright
 * @date 2021/3/16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Draft {
    /**
     *  作者编号
     */
    private Integer authorId;
    /**
     *  草稿标题
     */
    private String draftTitle;
    /**
     *  草稿内容
     */
    private String draftContent;
}
