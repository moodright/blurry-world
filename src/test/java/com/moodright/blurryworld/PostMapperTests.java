package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author moodright
 * @date 2021/3/14
 */

@SpringBootTest
public class PostMapperTests {

    @Autowired
    PostService postService;

    @Test
    public void addPostTest() {
        Post post = new Post();
        post.setAuthorId(1001);
        post.setPostTitle("Ocean Eyes");
        post.setPostType(0);
        post.setPostDescription("xxx");
        post.setPostContent("xxx");
        post.setPostCreateTime(new Date());
        post.setPostUpdateTime(new Date());
        post.setPostReleaseTime(new Date());
        postService.addPost(post);
    }

    @Test
    public void updatePostByPostIdTest() {
        Post post = new Post();
        post.setPostId(2000);
        post.setPostContent("I have watching you for some time~");
        postService.updatePostByPostId(post);
    }

    @Test
    public void queryPostByPostIdTest() {
        Post post = postService.queryPostByPostId(2000);
        System.out.println(post);
    }

    @Test
    public void queryAllPostsByUserIdTest() {
        List<Post> posts = postService.queryAllPostsByUserId(1001);
        for (Post post : posts) {
            System.out.println(post);
        }
    }

}
