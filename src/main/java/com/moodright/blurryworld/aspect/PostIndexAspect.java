package com.moodright.blurryworld.aspect;

import com.alibaba.fastjson.JSON;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.PostIndex;
import com.moodright.blurryworld.service.PostIndexService;
import com.moodright.blurryworld.utils.ChineseSegmentUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/2
 */
@Aspect
@Component
public class PostIndexAspect {

    ChineseSegmentUtil chineseSegmentUtil;
    PostIndexService postIndexService;

    @Autowired
    public void setChineseSegmentUtil(ChineseSegmentUtil chineseSegmentUtil) {
        this.chineseSegmentUtil = chineseSegmentUtil;
    }

    @Autowired
    public void setPostIndexService(PostIndexService postIndexService) {
        this.postIndexService = postIndexService;
    }

    /**
     * 添加文章后置通知
     * 匹配 {@link com.moodright.blurryworld.service.PostService#addPost(Post)} 切入点
     * @param post 传递给切入点方法的封装好的文章实体类
     * @param returnValue 切入点方法执行后的返回值，用于分词判断
     */
    @AfterReturning(value = "execution(int com.moodright.blurryworld.service.PostService.addPost(..)) &&" +
                    "args(post)",
                    argNames = "post, returnValue",
                    returning = "returnValue")
    public void postContentSegmentAdvice(Post post, int returnValue) {
        if (returnValue > 0) {
            // 文章内容字符串清洗
            String cleaningPostContent = chineseSegmentUtil.originalPostContentCleaning(post.getPostContent());
            // 文章内容分词
            Map<String, Map<Integer, Integer>> postIndexMap = chineseSegmentUtil.segment(post.getPostId(), cleaningPostContent);
            // 遍历分词后获取的词索引名查询数据库是否有相同词记录
            for ( Map.Entry<String, Map<Integer, Integer>> entry : postIndexMap.entrySet()) {
                // 查询数据库匹配索引词
                PostIndex postIndex = postIndexService.queryPostIndexByIndexName(entry.getKey());
                if(postIndex == null) {
                    // 索引词不存在
                    PostIndex temp = new PostIndex();
                    temp.setIndexName(entry.getKey());
                    temp.setIndexMap(JSON.toJSONString(entry.getValue()));
                    postIndexService.addPostIndex(temp);
                } else {
                    // 索引词已存在
                    HashMap<Integer, Integer> existIndexMap = JSON.parseObject(postIndex.getIndexMap(), HashMap.class);
                    // 更新索引图
                    for ( Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
                        existIndexMap.put(entry1.getKey(), entry1.getValue());
                    }
                    postIndex.setIndexMap(JSON.toJSONString(existIndexMap));
                    postIndexService.updatePostIndex(postIndex);
                }
            }
        }
    }



}
