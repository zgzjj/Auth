package com.zjj.auth.handler;

import com.zjj.auth.common.AjaxResult;
import com.zjj.auth.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zjj
 * @desc
 * @date 2019/11/12 15:38
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public AjaxResult handlerBoxException(BaseException e){
        return AjaxResult.error(e.getCode(),e.getMessage());
    }

}
