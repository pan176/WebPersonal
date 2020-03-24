package com.pan176.webPersonal.business.service;


import com.pan176.webPersonal.business.domain.TbResume;

public interface TbResumeService {

    /**
     * 保存简历
     * @content 简历内容
     */
    void update(String content);

    /**
     * 获得简历内容
     * @return
     */
    TbResume get();
}
