package com.zjj.auth.controller.system;

import com.zjj.auth.common.JsonData;
import com.zjj.auth.entity.dto.SysDeptDto;
import com.zjj.auth.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/sysDept")
@Api(value="SysDeptController",tags={"部门操作接口"})
public class SysDeptController {

    private final static Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/create")
    @ResponseBody
    @ApiOperation(value="创建部门",notes="同一层级下不能出现同样名称的部门")
    public JsonData create(@Valid @RequestBody SysDeptDto sysDeptDto){
        return sysDeptService.save(sysDeptDto);
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value="更新部门")
    public JsonData update(@Valid @RequestBody SysDeptDto sysDeptDto){
        return sysDeptService.update(sysDeptDto);
    }

}
