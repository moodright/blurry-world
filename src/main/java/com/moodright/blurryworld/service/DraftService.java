package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.DraftMapper;
import com.moodright.blurryworld.pojo.Draft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moodright
 * @date 2021/3/16
 */

@Service
public class DraftService implements DraftMapper {

    DraftMapper draftMapper;

    @Autowired
    public void setDraftMapper(DraftMapper draftMapper) {
        this.draftMapper = draftMapper;
    }

    /**
     * 添加草稿
     *
     * @param draft 草稿对象
     * @return 受影响的行数
     */
    @Override
    public int addDraft(Draft draft) {
        return draftMapper.addDraft(draft);
    }

    /**
     * 根据作者编号删除草稿
     *
     * @param id 作者编号
     * @return 受影响的行数
     */
    @Override
    public int deleteDraftByAuthorID(Integer id) {
        return draftMapper.deleteDraftByAuthorID(id);
    }

    /**
     * 根据作者编号查询草稿
     *
     * @param id 作者编号
     * @return 草稿对象
     */
    @Override
    public Draft queryDraftByAuthorId(Integer id) {
        return draftMapper.queryDraftByAuthorId(id);
    }

    /**
     * 根据作者编号更新草稿
     *
     * @param draft@return 受影响的行数
     */
    @Override
    public int updateDraftByAuthorId(Draft draft) {
        return draftMapper.updateDraftByAuthorId(draft);
    }
}
