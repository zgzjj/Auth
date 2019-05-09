package com.zjj.auth.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统部门实体
 */
@Entity
@Table(name = "sys_dept")
@Data
public class SysDeptPo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dept_name")
    private String deptName;


    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "level")
    private String level;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "operator")
    private String operator;

    @Column(name = "operator_ip")
    private String operatorIp;

    @Column(name = "operator_time")
    private Date operatorTime;

    @Column(name = "remark")
    private String remark;

}
