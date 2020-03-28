package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbUser;
import com.pan176.webPersonal.business.dto.LoginParam;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbUserService;
import com.pan176.webPersonal.business.util.MapperUtils;
import com.pan176.webPersonal.business.util.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * <p>Title: UserController</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 14:36
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final TbUserService userService;

    private final TokenStore tokenStore;

    public UserController(BCryptPasswordEncoder passwordEncoder, TbUserService userService, TokenStore tokenStore) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.tokenStore = tokenStore;
    }

    /**
     * 用户登录
     * @param param 登录参数（username:用户名 password:密码）
     * @return 返回 token
     */
    @PostMapping("login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam param) {
        // 取出参数
        String username = param.getUsername();
        String password = param.getPassword();

        // 是否已登录
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // 密码不匹配
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("管理员账号密码错误");
        }

        // 封装参数
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", "password");
        params.put("client_id", "client");
        params.put("client_secret", "secret");

        // 得到 Token，封装返回结果
        Map<String, Object> result = new HashMap<>();
        try {
            Response response = OkHttpClientUtil.getInstance().postData("http://127.0.0.1:9001/oauth/token", params);
            if (response.body() != null) {
                Map<String, Object> map = MapperUtils.json2map(response.body().string());
                String token = String.valueOf(map.get("access_token"));
                result.put("token", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "登录失败");
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "登录成功", result);
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping("getInfo")
    public ResponseResult<Map<String, Object>> getInfo() {
        // 获取角色
        TbUser user = userService.selectByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", roles);
        result.put("name", user.getUsername());
        result.put("avatar", user.getIcon());
        result.put("email", user.getEmail());

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取用户信息", result);
    }


    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        // 获取 Token
        String token = request.getParameter("access_token");

        // 删除 Token
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "用户已注销");
    }

    /**
     * 用户更新
     * @param user
     * @return
     */
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM', 'EDITOR')")
    public ResponseResult<Map<String, Object>> update(@RequestBody TbUser user) {
        userService.update(user);

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("path", user.getIcon());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新用户成功", result);
    }
}
