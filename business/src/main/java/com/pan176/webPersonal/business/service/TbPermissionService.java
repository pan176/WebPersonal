package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbPermission;

import java.util.List;

public interface TbPermissionService{

    List<TbPermission>  selectByUserId(Long id);

}
