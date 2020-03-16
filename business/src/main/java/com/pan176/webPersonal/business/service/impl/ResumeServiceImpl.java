package com.pan176.webPersonal.business.service.impl;

import javax.annotation.Resource;

import com.pan176.webPersonal.business.domain.Resume;
import com.pan176.webPersonal.business.mapper.ResumeMapper;
import com.pan176.webPersonal.business.service.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService{

    @Resource
    private ResumeMapper resumeMapper;

    @Override
    public void update(String content) {
        Resume resume = new Resume();
        // 默认都是 1
        resume.setId(1L);
        resume.setContent(content);
        resumeMapper.updateByPrimaryKey(resume);
    }

    @Override
    public Resume get() {
        return resumeMapper.selectByPrimaryKey(1L);
    }
}
