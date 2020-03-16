package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbUser;

public interface TbUserService{
    /**
     * 通过用户名得到用户
     * @param username 用户名
     * @return
     */
    TbUser getByUsername(String username);

    /**
     * 根据用户名更新
     * @param username 用户名
     * @param path 头像路径
     */
    void update(TbUser user);


}
