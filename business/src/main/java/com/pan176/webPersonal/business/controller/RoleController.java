package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbRole;
import com.pan176.webPersonal.business.dto.PermissionParam;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbRolePermissionService;
import com.pan176.webPersonal.business.service.TbRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 * <p>Title: RoleController</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 15:00
 */
@RequestMapping("role")
@RestController
public class RoleController {
    private final TbRoleService roleService;

    private final TbRolePermissionService rolePermissionService;

    public RoleController(TbRoleService roleService, TbRolePermissionService rolePermissionService) {
        this.roleService = roleService;
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping("list")
    public ResponseResult<List<PermissionParam>> list() {
        List<TbRole> roles = roleService.list();
        List<PermissionParam> result = new ArrayList<>();

        // 封装返回结果
        for (TbRole role : roles) {
            PermissionParam param = new PermissionParam();
            param.setRole(role);
            param.setPermissionList(rolePermissionService.selectPermissionIdList(role.getId()));
            result.add(param);
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "请求成功", result);
    }

    /**
     * 角色更新
     * @param param
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> update(@RequestBody PermissionParam param) {
        TbRole role = param.getRole();
        roleService.update(role);

        // 先删后加
        rolePermissionService.delete(role.getId());
        rolePermissionService.insert(role.getId(), param.getPermissionList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新成功");
    }

    /**
     * 角色新增
     * @param param
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<TbRole> add(@RequestBody PermissionParam param) {
        TbRole role = roleService.insert(param.getRole());

        // 中间表新增
        rolePermissionService.insert(role.getId(), param.getPermissionList());

        // 返回 ID
        return new ResponseResult<TbRole>(ResponseResult.CodeStatus.OK, "新增成功", role);
    }

    /**
     * 角色删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(Long id) {
        roleService.delete(id);
        rolePermissionService.delete(id);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功");
    }
}
