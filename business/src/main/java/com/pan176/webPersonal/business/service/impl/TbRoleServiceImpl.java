package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbRole;
import com.pan176.webPersonal.business.mapper.TbRoleMapper;
import com.pan176.webPersonal.business.service.TbRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

    @Override
    public List<TbRole> selectByUserId(Long id) {
        return tbRoleMapper.selectByUserId(id);
    }

    @Override
    public List<TbRole> list() {
        return tbRoleMapper.selectAll();
    }

    @Override
    public void delete(Long id) {
        tbRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(TbRole role) {
        role.setUpdated(new Date());
        tbRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public TbRole insert(TbRole role) {
        role.setCreated(new Date());
        role.setUpdated(new Date());
        role.setParentId(0L);
        tbRoleMapper.insertSelective(role);
        return role;
    }
}
