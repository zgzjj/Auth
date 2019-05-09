package com.zjj.auth.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户实体
 */
@Entity
@Table(name = "sys_user")
@Data
public class SysUserPo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "user_cn_name")
    private String userCnName;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "operator")
    private String operator;

    @Column(name = "operator_ip")
    private String operatorIp;

    @Column(name = "operator_time")
    private Date operatorTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remark")
    private String remark;
}
