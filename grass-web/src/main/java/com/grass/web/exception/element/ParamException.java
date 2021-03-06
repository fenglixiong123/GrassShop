package com.grass.web.exception.element;


import com.grass.common.enums.ErrorMsgEnum;
import com.grass.web.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.16 16:52
 * @Description 传参异常
 **/
public class ParamException extends BaseException {

    public ParamException(String message) {
        super(message);
    }

    public ParamException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }

}
