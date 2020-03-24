package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbPermission;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private TbPermissionService permissionService;

    @GetMapping("list")
    public ResponseResult<List<Map<String, Object>>> list() {
        return new ResponseResult<List<Map<String, Object>>>(ResponseResult.CodeStatus.OK, "权限列表", permissionService.list());
    }
}
