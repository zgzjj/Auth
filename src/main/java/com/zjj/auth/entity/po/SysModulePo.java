package com.zjj.auth.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "sys_module")
public class SysModulePo extends BasePo{

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "level")
    private String level;

    @Column(name = "seq")
    private Integer seq;

}
