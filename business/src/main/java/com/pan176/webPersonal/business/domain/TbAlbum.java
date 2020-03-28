package com.pan176.webPersonal.business.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_album")
public class TbAlbum implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "`path`")
    private String path;

    @Column(name = "`describe`")
    private String describe;

    private static final long serialVersionUID = 1L;
}
