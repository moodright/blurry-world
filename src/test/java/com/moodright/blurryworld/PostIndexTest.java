package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.PostIndex;
import com.moodright.blurryworld.service.PostIndexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author moodright
 * @date 2021/5/2
 */
@SpringBootTest
public class PostIndexTest {

    @Autowired
    PostIndexService postIndexService;

    @Test
    public void queryPostIndexByIndexNameTest() {
        PostIndex postIndex = postIndexService.queryPostIndexByIndexName("111");
        System.out.println(postIndex);
    }

    @Test
    public void addPostIndexTest() {
        PostIndex postIndex = new PostIndex();
        postIndex.setIndexMap("ouo");
        postIndex.setIndexName("=w=");
        postIndexService.addPostIndex(postIndex);
    }

    @Test
    public void updatePostIndexTest() {
        PostIndex postIndex = new PostIndex();
        postIndex.setIndexMap("新杰");
        postIndex.setIndexName("111");
        postIndexService.updatePostIndex(postIndex);
    }
}
