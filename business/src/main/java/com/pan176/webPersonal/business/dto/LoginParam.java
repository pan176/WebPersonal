package com.pan176.webPersonal.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 * <p>Title: LoginParam</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 14:20
 */
@Data
public class LoginParam implements Serializable {
    private static final long serialVersionUID = -4879151584626097650L;

    private String username;

    private String password;
}
