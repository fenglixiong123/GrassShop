package com.grass.web.exception.handler;


import com.grass.common.enums.ErrorMsgEnum;
import com.grass.web.exception.element.*;
import com.grass.common.result.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2018.11.10 14:11
 * @Description 全局异常处理类，注意要被引用的项目扫描到才行
 **/
@ResponseBody
@ControllerAdvice
public class CoreExceptionHandler implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(CoreExceptionHandler.class);

    /**
     * 登录异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserLoginException.class)
    public ResultResponse userLoginExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】认证异常userLoginException：{}",e);
        return ResultResponse.error(ErrorMsgEnum.LOGIN_ERROR,e.getMessage());
    }

    /**
     * 认证异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnAuthorizedException.class)
    public ResultResponse unAuthorizedExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】认证异常unAuthorizedException：{}",e);
        return ResultResponse.error(ErrorMsgEnum.UN_LOGIN,e.getMessage());
    }

    /**
     * 锁定异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserLockedException.class)
    public ResultResponse userLockedExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】认证异常userLockedException：{}",e);
        return ResultResponse.error(ErrorMsgEnum.LOGIN_LOCK,e.getMessage());
    }


    /**
     * 权限异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessForbiddenException.class)
    public ResultResponse accessForbiddenExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】权限异常noPermissionException：{}",e);
        return ResultResponse.error(ErrorMsgEnum.NO_PERMISSION);
    }

    /**
     * 传参异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ParamException.class)
    public ResultResponse<String> paramExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】传参异常paramException：{}",e);
        return new ResultResponse<String>().fail(ErrorMsgEnum.PARAM_INVILAD.getCode(),ErrorMsgEnum.PARAM_INVILAD.getMsg(),e.getMessage());
    }

    /**
     * Hibernate校验异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse methodNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】字段校验异常MethodArgumentNotValidException：{}",e);
        String errorMsg = "字段校验异常";
        if(e.getBindingResult().getFieldError()!=null){
            errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResultResponse.error(ErrorMsgEnum.PARAM_CHECK,errorMsg);
    }

    /**
     * 传参异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultResponse<String> missingRequestParamExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】缺少参数异常missingRequestParamException：{}",e);
        return new ResultResponse<String>().fail(ErrorMsgEnum.PARAM_MISSING.getCode(),ErrorMsgEnum.PARAM_MISSING.getMsg(),e.getMessage());
    }

    /**
     * Json转换异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultResponse jsonConvertExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】业务异常JsonConvertException：{}",e);
        return ResultResponse.error(ErrorMsgEnum.JSON_CONVERT_ERROR);
    }

    /**
     * 业务异常类
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public ResultResponse bizExceptionHandler(HttpServletRequest request, BizException e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        log.error("【异常类型】业务异常BizException：{}",e);
        return ResultResponse.error(e.getMessage());
    }

    /**
     * 全局异常类
     */
    @ExceptionHandler(Exception.class)
    public ResultResponse defaultExceptionHandler(HttpServletRequest request, Exception e){
        log.error("【异常地址】：{}",request.getRequestURL().toString());
        String message;
        if(e instanceof NullPointerException){
            message = "空指针异常";
            log.error("【异常类型】空指针异常NullPointerException：{}",e);
        } else if(e instanceof IllegalArgumentException){
            message = "参数异常";
            log.error("【异常类型】参数异常IllegalArgumentException：{}",e);
        }else if(e instanceof ArithmeticException){
            message = "算数异常";
            log.error("【异常类型】算数异常ArithmeticException：{}",e);
        }else {
            message = "未知异常";
            log.error("【异常类型】未知异常UnknownException：{}",e);
        }

        return ResultResponse.error(message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------->CoreExcept init<-------------");
    }
}
