package com.pan176.webPersonal.business.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.pan176.webPersonal.business.dto.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 上传文件服务
 * <p>Title: UploadController</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/12 14:38
 */
@RestController
@RequestMapping(value = "upload")
public class UploadController {
    /**
     * 相关配置参数
     */
    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "ACCESS_KEY_ID";
    private static final String ACCESS_KEY_SECRET = "ACCESS_KEY_SECRET";
    private static final String BUCKET_NAME = "BUCKET_NAME";

    /**
     * 文件上传
     * @param multipartFile @{code MultipartFile}
     * @return {@link ResponseResult<Map<String, String>>} 文件上传路径
     */
    @PostMapping(value = "")
    public ResponseResult<Map<String, String>> upload(MultipartFile multipartFile) {
        // 1. 得到文件名、后缀、再随机生成新名字
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;

        // 2. 创建客户端上传文件
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, newName, new ByteArrayInputStream(multipartFile.getBytes())));

            Map<String, String> map = new HashMap<>();
            map.put("path", "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + newName);
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            // 返回路径
            return new ResponseResult<Map<String, String>>(ResponseResult.CodeStatus.FAIL, "文件上传成功", map);
        } catch (IOException e) {
            return new ResponseResult<Map<String, String>>(ResponseResult.CodeStatus.FAIL, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }
}
