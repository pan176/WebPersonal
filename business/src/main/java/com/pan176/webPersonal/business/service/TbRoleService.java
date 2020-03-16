package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbRole;

import java.util.List;

public interface TbRoleService{

    List<TbRole> selectByUserId(Long id);
}
