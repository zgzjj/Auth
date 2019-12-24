package com.zjj.auth.controller.system;

import com.zjj.auth.common.AjaxResult;
import com.zjj.auth.entity.po.SysUserPo;
import com.zjj.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zjj
 * @desc
 * @date 2019/12/24 11:11
 */
@RestController
@RequestMapping("/api/auth/user/")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @PostMapping("create")
    public AjaxResult createUser(@RequestBody SysUserPo po) {
        SysUserPo user = userService.save(po);
        return AjaxResult.success(user);
    }
}
