package com.moodright.blurryworld.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询工具类
 * @author moodright
 * @date 2021/3/10
 */

@Component
@Data
@NoArgsConstructor
public class PaginationUtil {
    // 全部数据记录
    private int totalCount;
    // 当前起始下标
    private int currentStartIndex;
    // 分页数量
    private int pageSize;
    // 数据库查询参数
    Map<String, Integer> paginationInfo = new HashMap<>();

    // 更新数据库查询参数
    // 分页大小
    public void updatePageSize(int pageSize) {
        paginationInfo.put("pageSize", pageSize);
    }
    // 总数
    public void updateTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    // 当前起始下标
    public void updateCurrentStartIndex(int currentStartIndex) {
        paginationInfo.put("currentStartIndex", currentStartIndex);
    }
    // 下一页下标
    public void updateNextPageIndex(int currentStartIndex) {
        paginationInfo.put("nextPageIndex", currentStartIndex + pageSize);
    }
    // 上一页下标
    public void updatePreviousPageIndex(int currentStartIndex) {
        paginationInfo.put("previousPageIndex", currentStartIndex - pageSize);
    }
    // 最后一页下标
    public void updateLastPageIndex() {
        paginationInfo.put("lastPageIndex", pageSize * ((int)Math.ceil(totalCount / (double)pageSize) - 1));
    }
    // 总页数
    public void updateTotalPage() {
        paginationInfo.put("totalPage",  (int)Math.ceil(totalCount / (double)pageSize));
    }

}
