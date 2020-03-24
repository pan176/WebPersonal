package com.pan176.webPersonal.business.mapper;

import com.pan176.webPersonal.business.domain.TbRolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbRolePermissionMapper extends MyMapper<TbRolePermission> {
    @Select("SELECT p.id FROM tb_role r, tb_role_permission rp, tb_permission p WHERE r.id = rp.role_id AND p.id = rp.permission_id AND r.id = #{id}")
    public List<Long> selectPermissionIdList(@Param("id") Long id);
}
