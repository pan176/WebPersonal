package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbPermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {
    private final TbPermissionService permissionService;

    public PermissionController(TbPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 资源列表
     * @return
     */
    @GetMapping("list")
    public ResponseResult<List<Map<String, Object>>> list() {
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "权限列表", permissionService.list());
    }
}
