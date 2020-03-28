package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbResume;
import com.pan176.webPersonal.business.mapper.TbResumeMapper;
import com.pan176.webPersonal.business.service.TbResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbResumeServiceImpl implements TbResumeService {

    @Resource
    private TbResumeMapper resumeMapper;

    @Override
    public void update(String content) {
        TbResume resume = new TbResume();

        // 默认都是 1
        resume.setId(1L);
        resume.setContent(content);
        resumeMapper.updateByPrimaryKeySelective(resume);
    }

    @Override
    public TbResume get() {
        return resumeMapper.selectByPrimaryKey(1L);
    }
}
