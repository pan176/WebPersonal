package com.pan176.webPersonal.business.mapper;

import com.pan176.webPersonal.business.domain.TbAlbum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbAlbumMapper extends MyMapper<TbAlbum> {


    @Select("SELECT * FROM tb_album ORDER BY RAND() LIMIT #{num}")
    public List<TbAlbum> randList(@Param("num") Integer num);
}
