package com.pan176.webPersonal.business.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.pan176.webPersonal.business.dto.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    private static final String ENDPOINT = "ENDPOINT";
    private static final String BUCKET_NAME = "BUCKET_NAME";
    private static final String ACCESS_KEY_ID = "ACCESS_KEY_ID";
    private static final String ACCESS_KEY_SECRET = "ACCESS_KEY_SECRET";

    /**
     * 文件上传
     *
     * @param multipartFile @{code MultipartFile}
     * @return {@link ResponseResult<Map<String, String>>} 文件上传路径
     */
    @PostMapping(value = "")
    @PreAuthorize("hasAnyAuthority('SYSTEM', 'EDITORY')")
    public ResponseResult<Map<String, String>> upload(MultipartFile multipartFile, String folder) {
        // 1. 得到文件名、后缀、再随机生成新名字
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;

        // 2. 创建客户端上传文件
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, folder + "/" + newName, new ByteArrayInputStream(multipartFile.getBytes())));

            Map<String, String> map = new HashMap<>();
            map.put("path", "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + folder + "/" + newName);
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            // 返回路径
            return new ResponseResult<Map<String, String>>(ResponseResult.CodeStatus.FAIL, "文件上传成功", map);
        } catch (IOException e) {
            return new ResponseResult<Map<String, String>>(ResponseResult.CodeStatus.FAIL, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }

    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(String path) {
        // 1. 创建客户端上传文件
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 2. 删除文件
            String objectName = path.substring(path.lastIndexOf("/album") + 1);
            client.deleteObject(BUCKET_NAME, objectName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "文件删除失败，请重试");
        } finally {
            // 关闭OSSClient。
            client.shutdown();
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "文件删除成功");
    }
}
