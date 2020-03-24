package com.pan176.webPersonal.business.service.impl;

import com.pan176.webPersonal.business.domain.TbAlbum;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pan176.webPersonal.business.mapper.TbAlbumMapper;
import com.pan176.webPersonal.business.service.TbAlbumService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TbAlbumServiceImpl implements TbAlbumService{

    @Resource
    private TbAlbumMapper albumMapper;

    @Override
    public void insert(TbAlbum album) {
        albumMapper.insertSelective(album);
    }

    @Override
    public List<TbAlbum> list() {
        return albumMapper.selectAll();
    }

    @Override
    public void delete(String path) {
        Example example = new Example(TbAlbum.class);
        example.createCriteria().andEqualTo("path", path);
        albumMapper.deleteByExample(example);
    }

    @Override
    public List<TbAlbum> randList(Integer num) {
        return albumMapper.randList(num);
    }


}
