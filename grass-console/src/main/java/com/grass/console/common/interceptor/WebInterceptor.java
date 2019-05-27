package com.grass.console.common.interceptor;

import com.grass.common.enums.ErrorMsgEnum;
import com.grass.console.common.constants.WebConstant;
import com.grass.web.exception.element.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Fenglixiong
 * @Create 2019/5/24 0:17
 * @Description
 **/
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    private static final String USER_TOKEN = "X-TOKEN";

    private static String[] writeUrl = {
            "/console/admin/login",
            "/console/admin/getToken"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUrl = request.getRequestURI();
        for (int i = 0; i < writeUrl.length; i++) {
            if(writeUrl[i].equals(currentUrl)){
                log.info("------>检测到时白名单，通过！");
                return true;
            }
        }
        log.info("currentUrl:{}",currentUrl);
        String c_token = request.getHeader(USER_TOKEN);
        String s_token = (String)request.getSession().getAttribute(WebConstant.USER_TOKEN);
        log.info("clientToken:{},realToken:{}",c_token,s_token);
        if(c_token==null||!c_token.equals(s_token)){
            throw new UnAuthorizedException(ErrorMsgEnum.UN_LOGIN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}