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

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            // 根据分词后的索引词组查询数据库中已存在的索引词组list
            List<PostIndex> postIndexList = postIndexService.queryPostIndexByPostIndexMap(postIndexMap);
            // 将数据库中已存在的索引词组list转换为map
            Map<String, String> existPostIndexMap = new HashMap<>();
            for ( PostIndex postIndex : postIndexList) {
                existPostIndexMap.put(postIndex.getIndexName(), postIndex.getIndexMap());
            }
            // 新增索引词组map
            Map<String, String> notExistPostIndexMap = new HashMap<>();
            for (Map.Entry<String, Map<Integer, Integer>> entry : postIndexMap.entrySet()) {
                if (!existPostIndexMap.containsKey(entry.getKey())) {
                    // 索引词不存在
                    notExistPostIndexMap.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
                } else {
                    // 索引词已存在
                    // 文章索引词map
                    Map<Integer, Integer> segmentIndexMap = postIndexMap.get(entry.getKey());
                    // 数据库表中索引词map
                    Map<Integer, Integer> indexMap = JSON.parseObject(existPostIndexMap.get(entry.getKey()), HashMap.class);
                    // 更新数据库表中索引词map
                    indexMap.put(post.getPostId(), segmentIndexMap.get(post.getPostId()));
                    existPostIndexMap.put(entry.getKey(), JSON.toJSONString(indexMap));
                }
            }
            // 插入新增索引词组map
            if(!notExistPostIndexMap.isEmpty()) {
                postIndexService.addPostIndex(notExistPostIndexMap);
            }
            // 更新已存在索引词组map
            if(!existPostIndexMap.isEmpty())
            {
                postIndexService.updatePostIndex(existPostIndexMap);
            }
        }
    }



}
