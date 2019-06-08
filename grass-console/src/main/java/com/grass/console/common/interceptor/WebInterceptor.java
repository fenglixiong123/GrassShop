package com.grass.console.common.interceptor;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.common.enums.ErrorMsgEnum;
import com.grass.console.common.constants.WebConstant;
import com.grass.web.exception.element.AccessForbiddenException;
import com.grass.web.exception.element.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUrl = request.getRequestURI();
        String method = request.getMethod();
        log.info("currentUrl:{},method:{}",currentUrl,method);
        for (int i = 0; i < writeUrl.length; i++) {
            if(writeUrl[i].equals(currentUrl)){
                log.info("------>检测到时白名单，通过！");
                return true;
            }
        }

        //判断是否登录

        String clientToken = request.getHeader(USER_TOKEN);
        String sessionToken = (String)request.getSession().getAttribute(WebConstant.USER_TOKEN);
        log.info("clientToken:{}",clientToken);
        log.info("sessionToken:{}",sessionToken);
        if(clientToken==null||!clientToken.equals(sessionToken)){
            throw new UnAuthorizedException(ErrorMsgEnum.UN_LOGIN);
        }

        //判断是否有权限
        log.info("检测到已经登录，开始检查权限");
        Object powerObj = request.getSession().getAttribute(WebConstant.USER_POWER);
        if(powerObj==null){
            throw new UnAuthorizedException(ErrorMsgEnum.UN_LOGIN);
        }
        @SuppressWarnings("unchecked")
        List<PowerVo> powerVos = (List<PowerVo>) powerObj;
        for(PowerVo powerVo : powerVos){
            if(powerVo.getPath().equals(currentUrl) && powerVo.getMethod().equals(method)){
                log.info("权限正常，放行！");
                return true;
            }
        }
        log.error("暂无权限，禁止访问！");
        throw new AccessForbiddenException(ErrorMsgEnum.NO_PERMISSION);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
