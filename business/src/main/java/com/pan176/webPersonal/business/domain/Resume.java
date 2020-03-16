package com.pan176.webPersonal.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "resume")
public class Resume implements Serializable {
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
