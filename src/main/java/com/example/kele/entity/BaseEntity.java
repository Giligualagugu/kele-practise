package com.example.kele.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xukele
 * @since 2019/6/6 21:12
 * kele-practise & com.example.kele.entity
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 8426275084216710542L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdTime")
    private Date createTime;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateTime")
    private Date updateTime;
}
