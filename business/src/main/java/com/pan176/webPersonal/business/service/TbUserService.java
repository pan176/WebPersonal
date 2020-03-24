package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbUser;

import java.util.Map;

public interface TbUserService{
    /**
     * 通过用户名得到用户
     * @param username 用户名
     * @return
     */
    TbUser selectByUsername(String username);

    /**
     * 更新
     * @param user 用户
     */
    void update(TbUser user);
}
