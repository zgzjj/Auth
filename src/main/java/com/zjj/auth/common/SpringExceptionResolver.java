package com.zjj.auth.common;

import com.zjj.auth.exception.PermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SpringExceptionResolver implements HandlerExceptionResolver{
    private static Logger log = LoggerFactory.getLogger(SpringExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System Error";
        if(e instanceof PermissionException){
            JsonData result = JsonData.fail(e.getMessage());
            log.error("exception ,url:"+url,e);
            mv = new ModelAndView("jsonView",result.toMap());
        }else {
            log.error("unknown exception ,url:"+url,e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
