package com.pan176.webPersonal.business.service;

import com.pan176.webPersonal.business.domain.TbAlbum;

import java.util.List;

public interface TbAlbumService{

    /**
     * 新增
     * @param album
     */
    void insert(TbAlbum album);

    /**
     * 返回列表
     * @return
     */
    List<TbAlbum> list();

    /**
     * 删除
     */
    void delete(String path);

    /**
     * 随机获取几条数据
     * @param num 指定数量
     * @return
     */
    List<TbAlbum> randList(Integer num);
}
