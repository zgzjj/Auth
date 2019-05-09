package com.zjj.auth.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="SysDeptDto对象",description="部门对象")
public class SysDeptDto {

    @ApiModelProperty(value="部门id",name="id")
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @ApiModelProperty(value="部门名称",name="deptName",required=true)
    private String deptName;

    @ApiModelProperty(value="父级部门Id",name="parentId")
    private Integer parentId;

    @ApiModelProperty(value="部门排序",name="seq")
    private Integer seq;

    @ApiModelProperty(value="部门说明",name="seq")
    @Length(max = 200, message = "最多不超过200字")
    private String remark;
}
