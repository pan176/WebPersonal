package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbUser;
import com.pan176.webPersonal.business.mapper.TbUserMapper;
import com.pan176.webPersonal.business.service.TbUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);

        // 仅返回一个
        return tbUserMapper.selectOneByExample(example);
    }

    @Override
    public void update(TbUser user) {
        TbUser oldUser = getByUsername(user.getUsername());
        user.setId(oldUser.getId());

        // 只更新实体类已设置的属性
        tbUserMapper.updateByPrimaryKey(user);
    }
}
