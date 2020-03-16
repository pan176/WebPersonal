package com.pan176.webPersonal.business.service.impl;


import javax.annotation.Resource;

import com.pan176.webPersonal.business.domain.TbPermission;
import com.pan176.webPersonal.business.mapper.TbPermissionMapper;
import com.pan176.webPersonal.business.service.TbPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long id) {
        return tbPermissionMapper.selectByUserId(id);
    }
}
