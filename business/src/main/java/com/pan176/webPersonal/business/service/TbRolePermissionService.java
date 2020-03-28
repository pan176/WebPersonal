package com.pan176.webPersonal.business.service;

import java.util.List;
import java.util.Map;

public interface TbRolePermissionService{

    /**
     * 一个角色ID对应多个权限
     * @param roleId
     * @param permissionList
     */
    void insert(Long roleId, List<Long> permissionList);

    /**
     * 根据角色 ID删除
     * @param roleId
     */
    void delete(Long roleId);

    /**
     * 得到权限 ID集合
     * @param roleId
     * @return
     */
    List<Long> selectPermissionIdList(Long roleId);
}
