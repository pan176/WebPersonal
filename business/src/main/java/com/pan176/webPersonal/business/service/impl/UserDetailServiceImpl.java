package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbRole;
import com.pan176.webPersonal.business.domain.TbUser;
import com.pan176.webPersonal.business.service.TbRoleService;
import com.pan176.webPersonal.business.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private TbUserService userService;

    @Autowired
    private TbRoleService tbRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        // 从数据库查询用户
        TbUser user = userService.selectByUsername(s);
        if (user != null && user.getStatus() != 0) {
            // 授予用户角色
            List<TbRole> tbRoles = tbRoleService.selectByUserId(user.getId());
            tbRoles.forEach(tbRole -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbRole.getEnname());
                authorities.add(grantedAuthority);
            });

            return new User(user.getUsername(), user.getPassword(), authorities);
        }

        return null;
    }
}
