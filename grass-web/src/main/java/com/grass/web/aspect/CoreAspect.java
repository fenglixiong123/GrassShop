package com.grass.web.aspect;


import com.grass.common.utils.json.JsonUtils;
import com.grass.web.annotation.RequestLimit;
import com.grass.web.annotation.SysLog;
import com.grass.web.utils.IPUtil;
import com.grass.web.utils.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:54
 * @Description 核心AOP类可以拦截所有的方法
 **/
@Aspect
@Component
public class CoreAspect implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(CoreAspect.class);

    @Value("${app.aspect.log.switcher:false}")
    private boolean switcher;

    /**
     * 最大搜索字段长度
     */
    private static final int MAX_SEARCH_LENGTH = 3;

    /**
     * execution()语法:execution (* com.xx.xx.impl..*.*(..))
     * 第一个*表示方法返回值类型[*表示所有类型]
     * com.xx.xx.impl表示包路径[*表示所有包]
     * .[.表示当前包下所有类的方法,..表示当前包下及此包下所有子包中的所有类的方法]
     * 第二个*表示类名[*表示所有类,可以匹配以X开头或结尾如X*、*X、X*X的类名]
     * 第三个*表示方法名[*表示所有方法,可以匹配以X开头或结尾的如X*、*X、X*X的方法名]
     * (..)表示方法参数[..表示任何参数]
     */
    @Pointcut(value = "execution(* com.grass..*.*(..))")
    private void serviceCommon(){

    }

    /**
     * 定义请求日志切入点
     * @param sysLog
     */
    @Pointcut(value = "@annotation(sysLog)")
    public void serviceSysLog(SysLog sysLog){

    }

    /**
     * 定义请求次数限制
     * @param requestLimit
     */
    @Pointcut(value = "@annotation(requestLimit)")
    public void serviceRequestLimit(RequestLimit requestLimit){

    }

    /**
     * 前置通知
     * @param
     * @param requestLimit
     */
    @Before(value = "serviceRequestLimit(requestLimit)")
    public void doBefore(JoinPoint joinPoint, RequestLimit requestLimit){
        log.info("RequestLimit:count->{},time->{}",requestLimit.count(),requestLimit.time());

    }

    /**
     * 后置通知
     * @param joinPoint
     * @param sysLog
     */
    @AfterReturning(pointcut = "serviceSysLog(sysLog)",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result , SysLog sysLog){
        //获取切点参数
        processReturnJoinPoint(joinPoint,result);
    }


    /**
     * 处理返回通知切点
     * @param joinPoint
     * @param result
     */
    private void processReturnJoinPoint(JoinPoint joinPoint, Object result) {

        String classPath = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] paramArgs = joinPoint.getArgs();
        HttpServletRequest request = WebUtil.getRequest();
        String requestUrl = WebUtil.getRequestUrl(request);
        String ipAddress = IPUtil.getClientIp(request);
        if(switcher) {
            log.info("classpath:{}", classPath);
            log.info("methodName:{}", methodName);
            log.info("paramJson:{}", JsonUtils.toJsonMsg(paramArgs));
            log.info("resultJson:{}", JsonUtils.toJsonMsg(result));
            log.info("iPAddress:{}", ipAddress);
            log.info("requestUrl:{}", requestUrl);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------->CoreAspect init<-------------");
    }
}
