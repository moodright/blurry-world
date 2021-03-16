package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Draft;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author moodright
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface DraftMapper {

    /**
     * 添加草稿
     * @param draft 草稿对象
     * @return 受影响的行数
     */
    int addDraft(Draft draft);

    /**
     * 根据作者编号删除草稿
     * @param id 作者编号
     * @return 受影响的行数
     */
    int deleteDraftByAuthorID(@Param("authorId")Integer id);

    /**
     * 根据作者编号查询草稿
     * @param id 作者编号
     * @return 草稿对象
     */
    Draft queryDraftByAuthorId(@Param("authorId")Integer id);

    /**
     * 根据作者编号更新草稿
     * @param draft 草稿对象
     * @return 受影响的行数
     */
    int updateDraftByAuthorId(Draft draft);
}
