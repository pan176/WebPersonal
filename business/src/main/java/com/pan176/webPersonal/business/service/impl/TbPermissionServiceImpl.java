package com.pan176.webPersonal.business.service.impl;


import com.pan176.webPersonal.business.domain.TbPermission;
import com.pan176.webPersonal.business.mapper.TbPermissionMapper;
import com.pan176.webPersonal.business.service.TbPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper permissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long id) {
        return permissionMapper.selectByUserId(id);
    }

    @Override
    public List<Map<String, Object>> list() {
        return findTreeByParentId(permissionMapper.selectAll(), 0L);
    }

    /**
     * 根据父类ID，返回树形结构集合
     * @param permissions
     * @param parentId
     * @return
     */
    private List<Map<String, Object>> findTreeByParentId(List<TbPermission> permissions, Long parentId) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (TbPermission permission : permissions) {
            if (parentId.equals(permission.getParentId())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", permission.getId());
                map.put("label", permission.getEnname());
                map.put("url", permission.getUrl());
                map.put("children", findTreeByParentId(permissions, permission.getId()));
                list.add(map);
            }
        }

        return list;
    }
}
