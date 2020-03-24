package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbUser;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbUserService;
import com.pan176.webPersonal.business.util.MapperUtils;
import com.pan176.webPersonal.business.util.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("user")
public class UserController {
    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TbUserService userService;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 登录
     * @param param 登录参数（username:用户名 password:密码）
     * @return 返回 token
     */
    @PostMapping("login")
    public ResponseResult<Map<String, Object>> login(@RequestBody Map<String, String> param) {
        // 1. 取出参数
        String username = param.get("username");
        String password = param.get("password");

        // 2. 查询是否已登录
        UserDetails user = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        //  用户不存在或密码错误
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("管理员账号密码错误");
        }

        // 3. 封装参数
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", "grant_type");
        params.put("client_id", "client_id");
        params.put("client_secret", "client_secret");

        // 4. 返回 Token
        Map<String, Object> result = new HashMap<>();
        try {
            Response response = OkHttpClientUtil.getInstance().postData("http://127.0.0.1:9001/oauth/token", params);
            Map<String, Object> map = MapperUtils.json2map(response.body().string());
            String token = String.valueOf(map.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK, "登录成功", result);
    }

    /**
     * 登录后获得用户信息
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public ResponseResult<Map<String, Object>> getInfo() {
        TbUser user = userService.selectByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List roles = new ArrayList<String>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        // 封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", user.getUsername());
        map.put("avatar", user.getIcon());
        map.put("roles", roles);
        map.put("email", user.getEmail());
        map.put("user", user);

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK, "获取用户信息", map);
    }


    /**
     * 注销
     * @param request
     * @return
     */
    @PostMapping("logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        // 从请求中拿到 Token
        String token = request.getParameter("access_token");

        // 再从 tokenStore 中删除
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "用户已注销");
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM', 'EDITOR')")
    public ResponseResult<Map> update(@RequestBody TbUser user) {
        userService.update(user);

        // 封装返回对象
        Map map = new HashMap<>();
        map.put("path", user.getIcon());
        return new ResponseResult<Map>(ResponseResult.CodeStatus.OK, "更新用户成功", map);
    }
}
