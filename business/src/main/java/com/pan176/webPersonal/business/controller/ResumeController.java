package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbResume;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    private TbResumeService resumeService;

    /**
     * 更新简历
     * @param map 简历内容
     * @return
     */
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> update(@RequestBody Map<String, String> map) {
        String content = map.get("content");
        if (content != null && !content.equals("")) {
            resumeService.update(map.get("content"));
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "更新成功", null);
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "更新失败", null);
    }

    /**
     * 获得简历
     * @return
     */
    @GetMapping("")
    public ResponseResult<TbResume> get() {
        TbResume resume = resumeService.get();
        if (resume != null) {
            return new ResponseResult<TbResume>(ResponseResult.CodeStatus.OK, "获得简历成功", resume);
        }
        return new ResponseResult<TbResume>(ResponseResult.CodeStatus.FAIL, "获得简历失败", null);
    }
}
