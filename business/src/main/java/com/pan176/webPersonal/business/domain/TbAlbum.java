package com.pan176.webPersonal.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

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