package com.grass.web.exception.element;

import com.grass.common.enums.ErrorMsgEnum;
import com.grass.web.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2019/6/8 1:40
 * @Description 用户被锁定异常
 **/
public class UserLockedException extends BaseException {
    public UserLockedException(String message) {
        super(message);
    }
    public UserLockedException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
