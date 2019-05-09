package com.zjj.auth.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SysUserDto {

    private Integer id;

    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    @NotBlank(message = "密码不可以为空")
    @Length(min = 1, max = 20, message = "密码长度需要在20个字以内")
    private String password;

    private String avatar;

    @NotBlank(message = "电话号码不可以为空")
    @Length(min = 1, max = 13, message = "电话号码长度需要在13个字以内")
    private String telephone;

    @NotBlank(message = "邮箱不可以为空")
    @Length(min = 1, max = 50, message = "邮箱长度需要在50个字以内")
    private String email;

    @NotNull(message = "必须提供用户所在的部门")
    private Integer deptId;

    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0 ,message = "用户状态不合法")
    @Max(value = 1 ,message = "用户状态不合法")
    private Integer status;

    @Length(min = 0 ,max = 200 , message = "备注长度需要在200字以内")
    private String remark="";
}
