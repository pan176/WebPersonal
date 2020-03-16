package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbRole;
import com.pan176.webPersonal.business.mapper.TbRoleMapper;
import com.pan176.webPersonal.business.service.TbRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

    @Override
    public List<TbRole> selectByUserId(Long id) {
        return tbRoleMapper.selectByUserId(id);
    }
}
