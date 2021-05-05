package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.PostIndex;
import com.moodright.blurryworld.service.PostIndexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/2
 */
@SpringBootTest
public class PostIndexTest {

    @Autowired
    PostIndexService postIndexService;

    @Test
    public void queryPostIndexByPostIndexMapTest() {
        Map<String, Map<Integer, Integer>> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(111, 222);
        map.put("缓存", map1);
        map.put("数据库", map1);
        map.put("属性", map1);

        List<PostIndex> postIndexList = postIndexService.queryPostIndexByPostIndexMap(map);


    }

    @Test
    public void addPostIndexTest() {
        Map<String, String> map = new HashMap<>();
        map.put("11" , "1");
        map.put("22", "1");
        map.put("33", "1");
        int i = postIndexService.addPostIndex(map);
    }

    @Test
    public void updatePostIndexTest() {
        Map<String, String> map = new HashMap<>();
        map.put("11" , "11111xxx");
        map.put("22", "22222xxx");
        map.put("33", "3333xxx");
        postIndexService.updatePostIndex(map);
    }
}
