package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbPermission;

import java.util.List;
import java.util.Map;

public interface TbPermissionService{

    /**
     * 根据 ID 获得权限列表
     * @param id
     * @return
     */
    List<TbPermission>  selectByUserId(Long id);

    /**
     * 获得列表
     * @return
     */
    List<Map<String, Object>> list();
}
