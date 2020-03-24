package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbAlbum;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbAlbumService;
import com.pan176.webPersonal.business.util.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private TbAlbumService albumService;

    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> add(@RequestBody TbAlbum album) {
        albumService.insert(album);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "新增成功");
    }

    @GetMapping("list")
    public ResponseResult<List<String>> list() {
        // 封装数据
        List<String> images = new ArrayList<>();
        List<TbAlbum> albums = albumService.list();
        for (TbAlbum album : albums) {
            images.add(album.getPath());
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "图片列表", images);
    }

    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(String path) {
        // 数据库中删除
        albumService.delete(path);

        // 返回要删除的图片名
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功");
    }

    @GetMapping("rand/{num}")
    public ResponseResult<List<String>> rand(@PathVariable("num") Integer num) {
        List<String> images = new ArrayList<>();
        List<TbAlbum> albums = albumService.randList(num);

        for (TbAlbum album : albums) {
            images.add(album.getPath());
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功", images);
    }
}
