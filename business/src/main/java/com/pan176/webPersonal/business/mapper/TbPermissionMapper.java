package com.pan176.webPersonal.business.mapper;

import com.pan176.webPersonal.business.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
    @Select("SELECT * FROM tb_permission WHERE id IN (\n" +
            "\tSELECT permission_id FROM tb_role_permission WHERE role_id IN( \n" +
            "\t\tSELECT role_id FROM tb_user_role WHERE user_id = #{id}\n" +
            "\t)\n" +
            ")\n" +
            "UNION\n" +
            "SELECT * FROM tb_permission WHERE parent_id IN (\n" +
            "\tSELECT id FROM tb_permission WHERE id IN (\n" +
            "\t\tSELECT permission_id FROM tb_role_permission WHERE role_id IN( \n" +
            "\t\t\tSELECT role_id FROM tb_user_role WHERE user_id = #{id}\n" +
            "\t\t)\n" +
            "\t)\n" +
            ")\n" +
            "UNION\n" +
            "SELECT * FROM tb_permission WHERE parent_id IN (\n" +
            "\tSELECT id FROM tb_permission WHERE parent_id IN (\n" +
            "\t\tSELECT id FROM tb_permission WHERE id IN (\n" +
            "\t\t\tSELECT permission_id FROM tb_role_permission WHERE role_id IN( \n" +
            "\t\t\t\tSELECT role_id FROM tb_user_role WHERE user_id = #{id}\n" +
            "\t\t\t)\n" +
            "\t\t)\n" +
            "\t)\n" +
            ")")
    List<TbPermission> selectByUserId(@Param("id") Long id);
}
