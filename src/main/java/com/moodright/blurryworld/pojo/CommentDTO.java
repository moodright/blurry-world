package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moodright
 * @date 2021/3/21
 */

@Data
@NoArgsConstructor
public class CommentDTO extends Comment {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;
}
