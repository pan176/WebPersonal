package com.pan176.webPersonal.business.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_resume")
public class TbResume implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    private static final long serialVersionUID = 1L;
}
