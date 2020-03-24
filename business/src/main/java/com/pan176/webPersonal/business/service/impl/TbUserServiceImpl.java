package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbUser;
import com.pan176.webPersonal.business.mapper.TbUserMapper;
import com.pan176.webPersonal.business.service.TbUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public TbUser selectByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);

        // 仅返回一个
        return userMapper.selectOneByExample(example);
    }

    @Override
    public void update(TbUser user) {
        user.setUpdateTime(new Date());

        userMapper.updateByPrimaryKey(user);
    }
}
