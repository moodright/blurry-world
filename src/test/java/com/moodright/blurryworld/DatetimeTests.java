package com.moodright.blurryworld;

import com.moodright.blurryworld.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author moodright
 * @date 2021/3/15
 */
@SpringBootTest
public class DatetimeTests {
    @Test
    public void datetimeLocalTest() {
        String releaseTime = "2021-03-12T12:11";
        String replace = releaseTime.replace("T", " ");
        Date date = DateUtil.releaseTimeStringToDate(replace);
        System.out.println(date);
    }
}
