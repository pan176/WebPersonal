package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbRole;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbRolePermissionService;
import com.pan176.webPersonal.business.service.TbRoleService;
import com.pan176.webPersonal.business.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("role")
@RestController
public class RoleController {
    @Autowired
    private TbRoleService roleService;

    @Autowired
    private TbRolePermissionService rolePermissionService;

    /**
     * 返回权限列表
     *
     * @return
     */
    @GetMapping("list")
    public ResponseResult<List<Map>> list() {
        List list = new ArrayList<Map>();

        // 封装数据
        List<TbRole> roles = roleService.list();
        for (TbRole role : roles) {
            Map map = new HashMap<>();
            map.put("id", role.getId());
            map.put("enname", role.getEnname());
            map.put("name", role.getName());
            map.put("description", role.getDescription());
            map.put("permissionList", rolePermissionService.selectPermissionIdList(role.getId()));
            list.add(map);
        }
        return new ResponseResult<List<Map>>(ResponseResult.CodeStatus.OK, "请求成功", list);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Transactional
    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(Long id) {
        roleService.delete(id);
        rolePermissionService.delete(id);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "");
    }

    @Transactional
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> update(@RequestBody Map<String, Object> map) throws Exception {
        // 去除 permissionList 再转
        Map roleMap = (Map) map.get("role");
        roleMap.remove("permissionList");
        TbRole role = MapperUtils.json2pojo(MapperUtils.mapToJson(roleMap), TbRole.class);
        roleService.update(role);

        // 先全部删除再添加
        rolePermissionService.delete(role.getId());
        rolePermissionService.insert(role.getId(), (List<Integer>) map.get("permissionList"));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新成功");
    }

    @Transactional
    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<TbRole> add(@RequestBody Map<String, Object> map) throws Exception {
        // 将 Map 通过工具转成 TbRole
        String roleJSON = MapperUtils.mapToJson((Map) map.get("role"));
        TbRole tbRole = roleService.insert(MapperUtils.json2pojo(roleJSON, TbRole.class));

        // 新增中间表
        rolePermissionService.insert(tbRole.getId(), (List<Integer>) map.get("permissionList"));

        // 返回新增 ID
        return new ResponseResult<TbRole>(ResponseResult.CodeStatus.OK, "新增成功", tbRole);
    }


}
