package com.grass.web.exception.element;


import com.grass.common.enums.ErrorMsgEnum;
import com.grass.web.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.14 17:23
 * @Description 用户认证异常
 **/
public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
