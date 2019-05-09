package com.zjj.auth.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    @NotBlank(message = "密码不可以为空")
    @Length(min = 1, max = 20, message = "密码长度需要在20个字以内")
    private String password;

}
