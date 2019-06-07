package com.grass.web.exception.element;

import com.grass.common.enums.ErrorMsgEnum;
import com.grass.web.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2019/6/8 1:42
 * @Description 用户登录异常
 **/
public class UserLoginException extends BaseException {

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
