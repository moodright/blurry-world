package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.Tag;
import com.moodright.blurryworld.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author moodright
 * @date 2021/5/5
 */
@SpringBootTest
public class TagTests {
    @Autowired
    TagService tagService;

    @Test
    public void addTagsTest() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(2000, "标签1"));
        tagList.add(new Tag(2000, "标签2"));
        tagList.add(new Tag(2000, "标签3"));
        tagService.addTags(tagList);
    }

    @Test
    public void queryTagsByPostIdTest() {
        List<Tag> tags = tagService.queryTagsByPostId(2000);
        for(Tag tag : tags) {
            System.out.println(tag);
        }
    }


}
