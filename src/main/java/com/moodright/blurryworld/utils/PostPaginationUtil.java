package com.moodright.blurryworld.utils;

import javafx.geometry.Pos;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章分页工具类
 * @author moodright
 * @date 2021/3/18
 */
@Component
@Data
@NoArgsConstructor
public class PostPaginationUtil {
    private Map<String, Integer> paginationInfo = new HashMap<>();

    /**
     * 每页分页数量初始化
     * @param pageSize 分页数量
     */
    public void initialPageSize(Integer pageSize) {
        this.paginationInfo.put("pageSize", pageSize);
    }

    /**
     * 更新分页信息
     * @param pageNumber 分页数量
     * @param authorId 作者编号
     * @param postsCount 当前分页请求下的所有文章数量
     */
    public void updatePaginationInfo(Integer pageNumber, Integer authorId, int postsCount) {
        // 更新作者编号
        this.paginationInfo.put("authorId", authorId);
        // 更新文章总数
        this.paginationInfo.put("totalPostsCount", postsCount);
        // 更新总页数
        this.paginationInfo.put("totalPagesCount", (int)Math.ceil(postsCount / (double)this.paginationInfo.get("pageSize")));
        // 更新最后一页
        this.paginationInfo.put("lastPage", this.paginationInfo.get("totalPagesCount"));
        // 更新上一页
        if(pageNumber == 1) {
            this.paginationInfo.put("prevPage", null);
        }else {
            this.paginationInfo.put("prevPage", pageNumber - 1);
        }
        // 更新下一页
        if(pageNumber < this.paginationInfo.get("totalPagesCount")) {
            this.paginationInfo.put("nextPage", pageNumber + 1);
        } else if(pageNumber.equals(this.paginationInfo.get("lastPage"))) {
            this.paginationInfo.put("nextPage", null);
        }
        // 更新查询信息
        if(pageNumber != 1) {
            this.paginationInfo.put("startIndex", (pageNumber - 1) * this.paginationInfo.get("pageSize"));
        }else {
            this.paginationInfo.put("startIndex", 0);
        }
    }

    public void updateCommentPaginationInfo(Integer pageNumber, Integer postId, int rootCommentsCount, int commentsCount) {
        // 更新文章编号作为查询条件
        this.paginationInfo.put("postId", postId);
        // 更新评论总数
        this.paginationInfo.put("totalCommentsCount", commentsCount);
        // 更新总页数
        this.paginationInfo.put("totalPagesCount", (int)Math.ceil(rootCommentsCount / (double)this.paginationInfo.get("pageSize")));
        // 更新最后一页
        this.paginationInfo.put("lastPage", this.paginationInfo.get("totalPagesCount"));
        // 更新上一页
        if(pageNumber == 1) {
            this.paginationInfo.put("prevPage", null);
        }else {
            this.paginationInfo.put("prevPage", pageNumber - 1);
        }
        // 更新下一页
        if(pageNumber < this.paginationInfo.get("totalPagesCount")) {
            this.paginationInfo.put("nextPage", pageNumber + 1);
        } else if(pageNumber.equals(this.paginationInfo.get("lastPage"))) {
            this.paginationInfo.put("nextPage", null);
        }
        // 更新查询信息
        if(pageNumber != 1) {
            this.paginationInfo.put("startIndex", (pageNumber - 1) * this.paginationInfo.get("pageSize"));
        }else {
            this.paginationInfo.put("startIndex", 0);
        }
    }

    public void updateCreateCenterCommentPaginationInfo(Integer pageNumber, Integer userId, int commentsCount) {
        // 更新用户编号作为查询条件
        this.paginationInfo.put("userId", userId);
        // 更新评论总数
        this.paginationInfo.put("totalCommentsCount", commentsCount);
        // 更新总页数
        this.paginationInfo.put("totalPagesCount", (int)Math.ceil(commentsCount / (double)this.paginationInfo.get("pageSize")));
        // 更新最后一页
        this.paginationInfo.put("lastPage", this.paginationInfo.get("totalPagesCount"));
        // 更新上一页
        if(pageNumber == 1) {
            this.paginationInfo.put("prevPage", null);
        }else {
            this.paginationInfo.put("prevPage", pageNumber - 1);
        }
        // 更新下一页
        if(pageNumber < this.paginationInfo.get("totalPagesCount")) {
            this.paginationInfo.put("nextPage", pageNumber + 1);
        } else if(pageNumber.equals(this.paginationInfo.get("lastPage"))) {
            this.paginationInfo.put("nextPage", null);
        }
        // 更新查询信息
        if(pageNumber != 1) {
            this.paginationInfo.put("startIndex", (pageNumber - 1) * this.paginationInfo.get("pageSize"));
        }else {
            this.paginationInfo.put("startIndex", 0);
        }
    }
}
