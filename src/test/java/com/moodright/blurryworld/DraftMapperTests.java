package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.Draft;
import com.moodright.blurryworld.service.DraftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author moodright
 * @date 2021/3/16
 */
@SpringBootTest
public class DraftMapperTests {

    @Autowired
    DraftService draftService;

    @Test
    public void addDraftTest() {
        Draft draft = new Draft();
        draft.setAuthorId(2222);
        draft.setDraftTitle("Lo Vas A Olvidar");
        draft.setDraftContent("Can you see me ? Do you know me ?");
        draftService.addDraft(draft);
    }


    @Test
    public void deleteDraftTest() {
        draftService.deleteDraftByAuthorID(2222);
    }

    @Test
    public void updateDraftTest() {
        Draft draft = new Draft();
        draft.setDraftContent("wwww");
        draft.setAuthorId(2222);
        draftService.updateDraftByAuthorId(draft);
    }

    @Test
    public void queryDraftTest() {
        Draft draft = draftService.queryDraftByAuthorId(2222);
        System.out.println(draft);
    }

}
