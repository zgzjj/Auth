package com.zjj.auth.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="SysModuleDto",description="权限模块对象")
public class SysModuleDto {

    @ApiModelProperty(value="权限模块id",name="id")
    private Integer id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 20, message = "权限模块名称的长度在2到20个字符")
    @ApiModelProperty(value="权限模块名称",name="deptName",required=true)
    private String name;

    @ApiModelProperty(value="父级权限模块Id",name="parentId")
    private Integer parentId=0;

    @ApiModelProperty(value="排序",name="seq")
    @NotNull(message = "权限模块的显示顺序不能为空")
    private Integer seq;

    @ApiModelProperty(value="状态",name="seq")
    @NotNull(message = "必须指定权限模块的状态")
    @Min(value = 0 ,message = "权限模块的状态不合法")
    @Max(value = 1 ,message = "权限模块的状态不合法")
    private Integer status;

    @ApiModelProperty(value="说明",name="seq")
    @Length(max = 200, message = "最多不超过200字")
    private String remark;
}
