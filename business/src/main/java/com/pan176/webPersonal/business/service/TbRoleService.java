package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbRole;

import java.util.List;

public interface TbRoleService{

    /**
     * 根据ID查角色
     * @param id
     * @return
     */
    List<TbRole> selectByUserId(Long id);

    /**
     * 权限列表
     * @return
     */
    List<TbRole> list();

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 更新
     * @param role
     */
    void update(TbRole role);

    /**
     * 新增
     * @param role
     */
    TbRole insert(TbRole role);
}
