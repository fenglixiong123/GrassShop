package com.grass.web.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2019/5/18 14:41
 * @Description
 **/
public class WebUtil {

    /**
     * 获取Http请求对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes!=null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取请求url
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request) {
        if(request!=null){
            return request.getRequestURI();
        }
        return null;
    }

}
