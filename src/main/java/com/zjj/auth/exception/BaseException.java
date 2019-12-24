package com.zjj.auth.exception;

import lombok.Getter;

/**
 * @author: zjj
 * @desc
 * @date 2019/12/24 11:37
 */
@Getter
public class BaseException extends RuntimeException{
    private Integer code = 500;

//    public BaseException(ResultEnum resultEnum){
//        super(resultEnum.getMessage());
//        this.code = resultEnum.getCode();
//    }

    public BaseException(String msg){
        super(msg);
    }
}
