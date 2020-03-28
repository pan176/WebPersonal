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
 * 云服务
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
     * 阿里云相关配置参数
     */
    private static final String ENDPOINT = "ENDPOINT";
    private static final String BUCKET_NAME = "BUCKET_NAME";
    private static final String ACCESS_KEY_ID = "ACCESS_KEY_ID";
    private static final String ACCESS_KEY_SECRET = "ACCESS_KEY_SECRET";

    /**
     * 上传图片
     * @param multipartFile 图片
     * @param folder 文件夹名
     * @return 图片地址
     */
    @PostMapping(value = "")
    @PreAuthorize("hasAnyAuthority('SYSTEM', 'EDITORY')")
    public ResponseResult<Map<String, String>> upload(MultipartFile multipartFile, String folder) {
        // 得到文件名、后缀、再随机生成新名字
        String fileName = multipartFile.getOriginalFilename();
        String suffix = null;
        if (fileName != null) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        String newName = UUID.randomUUID() + "." + suffix;

        // 创建客户端上传文件
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, folder + "/" + newName, new ByteArrayInputStream(multipartFile.getBytes())));

            Map<String, String> result = new HashMap<>();
            result.put("path", "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + folder + "/" + newName);
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "上传成功", result);
        } catch (IOException e) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "上传失败，请重试");
        } finally {
            // 关闭客户端
            client.shutdown();
        }
    }

    /**
     * 删除图片
     * @param path
     * @return
     */
    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(String path) {
        // 创建客户端上传文件
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 删除文件
            String objectName = path.substring(path.lastIndexOf("/album") + 1);
            client.deleteObject(BUCKET_NAME, objectName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "删除失败，请重试");
        } finally {
            // 关闭客户端
            client.shutdown();
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功");
    }
}
