package com.pan176.webPersonal.business.controller;

import com.pan176.webPersonal.business.domain.TbAlbum;
import com.pan176.webPersonal.business.dto.ResponseResult;
import com.pan176.webPersonal.business.service.TbAlbumService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/**
 * 相册管理
 * <p>Title: AlbumController</p>
 * <p>Description: </p>
 *
 * @author pan176
 * @version 1.0.0
 * @date 2020/3/26 19:05
 */
@RestController
@RequestMapping("album")
public class AlbumController {
    private final TbAlbumService albumService;

    public AlbumController(TbAlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * 图片新增
     * @param album
     * @return
     */
    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> add(@RequestBody TbAlbum album) {
        albumService.insert(album);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "新增成功");
    }

    /**
     * 图片列表
     * @return
     */
    @GetMapping("list")
    public ResponseResult<List<String>> list() {
        // 封装返回结果
        List<String> result = new ArrayList<>();
        List<TbAlbum> albums = albumService.list();
        for (TbAlbum album : albums) {
            result.add(album.getPath());
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "图片列表", result);
    }

    /**
     * 图片删除
     * @param path
     * @return
     */
    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    public ResponseResult<Void> delete(String path) {
        albumService.delete(path);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功");
    }

    /**
     * 返回指定数量图片列表
     * @param num
     * @return
     */
    @GetMapping("rand/{num}")
    public ResponseResult<List<String>> rand(@PathVariable("num") Integer num) {
        List<String> result = new ArrayList<>();
        List<TbAlbum> albums = albumService.randList(num);

        for (TbAlbum album : albums) {
            result.add(album.getPath());
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除成功", result);
    }
}
