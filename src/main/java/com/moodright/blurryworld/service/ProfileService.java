package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.ProfileMapper;
import com.moodright.blurryworld.pojo.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moodright
 * @date 2021/3/12
 */

@Service
public class ProfileService implements ProfileMapper {
    ProfileMapper profileMapper;

    @Autowired
    public void setProfileMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    /**
     * 根据用户编号初始化个人信息
     *
     * @param id 用户编号
     * @return 受影响的行数
     */
    @Override
    public int addProfileById(Integer id) {
        return profileMapper.addProfileById(id);
    }

    /**
     * 根据用户编号查询个人信息
     *
     * @param id 用户编号
     * @return 个人信息实体类
     */
    @Override
    public Profile queryProfileById(Integer id) {
        return profileMapper.queryProfileById(id);
    }

    /**
     * 根据用户编号更新个人信息
     *
     * @param profile 个人信息实体类
     * @return 受影响的行数
     */
    @Override
    public int updateProfileById(Profile profile) {
        return profileMapper.updateProfileById(profile);
    }
}
