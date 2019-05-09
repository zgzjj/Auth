package com.zjj.auth.controller;

import com.zjj.auth.common.JsonData;
import com.zjj.auth.entity.dto.LoginDto;
import com.zjj.auth.entity.po.SysUserPo;
import com.zjj.auth.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Api(value="UserController",tags={"普通用户操作接口"})
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;


    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value="用户登录")
    public JsonData login(@Valid @RequestBody LoginDto loginDto , HttpSession session){
        Map<String ,Object> param = new HashMap<>();
        param.put("EQ_username",loginDto.getUsername());
        String md5Password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        param.put("EQ_password",md5Password);
        SysUserPo userPo = sysUserService.findUser(param);
        if(userPo == null){
           return JsonData.fail("用户名或密码错误");
        }else{
            if(userPo.getStatus()!=1){
                return JsonData.fail("用户已被冻结，请联系管理员");
            }
            session.setAttribute("currentUser",userPo);
        }
        return JsonData.success(userPo,"登录成功");
    }

    @PostMapping("/logout")
    @ResponseBody
    @ApiOperation(value="用户注销")
    public JsonData logout(HttpSession httpSession){
        if(httpSession.getAttribute("currentUser")!=null){
            httpSession.removeAttribute("currentUser");
            return JsonData.success("用户已注销");
        }
        return JsonData.fail("用户还未登录");
    }
}
