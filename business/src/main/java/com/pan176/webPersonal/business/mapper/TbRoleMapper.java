package com.pan176.webPersonal.business.mapper;

import com.pan176.webPersonal.business.domain.TbRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbRoleMapper extends MyMapper<TbRole> {

    @Select("SELECT r.* FROM tb_user_role ur, tb_role r WHERE ur.role_id = r.id AND ur.user_id = #{id}")
    List<TbRole> selectByUserId(@Param("id") Long id);
}
