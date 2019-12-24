package com.zjj.auth.enums;

import lombok.Getter;

/**
 * @author: zjj
 * @desc
 * @date 2019/12/24 11:45
 */
@Getter
public enum BaseStatusEnum {

    STARTED(0,"正常"),
    STOPED(1,"停用")
    ;
    private Integer code;

    private String message;


    BaseStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
