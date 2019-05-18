package com.grass.web.exception.element;


import com.grass.web.enums.ErrorMsgEnum;
import com.grass.web.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.14 17:17
 * @Description
 **/
public class AccessForbiddenException extends BaseException {

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }

}
