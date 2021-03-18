package com.moodright.blurryworld.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/18
 */
@Component
@Data
@NoArgsConstructor
public class PostPaginationUtil {
    private Map<String, Integer> paginationInfo = new HashMap<>();
}
