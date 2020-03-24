package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbRolePermission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pan176.webPersonal.business.mapper.TbRolePermissionMapper;
import com.pan176.webPersonal.business.service.TbRolePermissionService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class TbRolePermissionServiceImpl implements TbRolePermissionService{

    @Resource
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public void insert(Long roleId, List<Integer> permissionList) {
        for (Integer id : permissionList) {
            TbRolePermission tbRolePermission = new TbRolePermission();
            // 先设置角色 ID
            tbRolePermission.setRoleId(roleId);
            // 设置权限 ID
            tbRolePermission.setPermissionId(id.longValue());

            // 新增
            tbRolePermissionMapper.insertSelective(tbRolePermission);
        }
    }

    @Override
    public void delete(Long roleId) {
        Example example = new Example(TbRolePermission.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        tbRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public List<Long> selectPermissionIdList(Long roleId) {
        return tbRolePermissionMapper.selectPermissionIdList(roleId);
    }
}
