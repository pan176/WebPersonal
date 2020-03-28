package com.pan176.webPersonal.business.dto;

import com.pan176.webPersonal.business.domain.TbRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限管理参数
 * <p>Title: PermissionParam</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 15:19
 */
@Data
public class PermissionParam implements Serializable {
    private static final long serialVersionUID = -6709868982326825966L;

    private TbRole role;

    private List<Long> permissionList;
}
