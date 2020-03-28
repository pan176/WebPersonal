package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbResume;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbResumeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简历管理
 * <p>Title: ResumeController</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 14:36
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    private final TbResumeService resumeService;

    public ResumeController(TbResumeService resumeService) {
        this.resumeService = resumeService;
    }

    /**
     * 简历更新
     * @param resume
     * @return
     */
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> update(@RequestBody TbResume resume) {
        String content = resume.getContent();
        if (content != null && !"".equals(content)) {
            resumeService.update(content);
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新成功", null);
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "更新失败，请重试", null);
    }

    /**
     * 获取简历
     * @return
     */
    @GetMapping("")
    public ResponseResult<TbResume> get() {
        TbResume resume = resumeService.get();
        if (resume != null) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取成功", resume);
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "获取失败，请重试", null);
    }
}
