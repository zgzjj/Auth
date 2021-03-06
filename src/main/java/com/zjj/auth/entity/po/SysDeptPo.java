package com.zjj.auth.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统部门实体
 */
@Data
@Entity
@Table(name = "sys_dept")
public class SysDeptPo extends BasePo{

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "level")
    private String level;

    @Column(name = "seq")
    private Integer seq;

}
