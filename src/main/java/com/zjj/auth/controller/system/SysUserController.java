package com.zjj.auth.controller.system;

import com.zjj.auth.common.JsonData;
import com.zjj.auth.entity.dto.SysUserDto;
import com.zjj.auth.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/sysUser")
@Api(value="SysUserController",tags={"系统管理用户操作接口"})
public class SysUserController {

    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/create")
    @ResponseBody
    @ApiOperation(value="创建用户")
    public JsonData create(@Valid @RequestBody SysUserDto sysUserDto){
        return sysUserService.save(sysUserDto);
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value="更新用户")
    public JsonData update(@Valid @RequestBody SysUserDto sysUserDto){
        return sysUserService.update(sysUserDto);
    }

}
