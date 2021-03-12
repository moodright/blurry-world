package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户个人信息映射器
 * @author moodright
 * @date 2021/3/12
 */

@Mapper
@Repository
public interface ProfileMapper {

    /**
     * 根据用户编号初始化个人信息
     * @param id 用户编号
     * @return 受影响的行数
     */
    int addProfileById(@Param("userId")Integer id);

    /**
     * 根据用户编号查询个人信息
     * @param id 用户编号
     * @return 个人信息实体类
     */
    Profile queryProfileById(@Param("userId")Integer id);

    /**
     * 根据用户编号更新个人信息
     * @param profile 个人信息实体类
     * @return
     */
    int updateProfileById(Profile profile);
}
