package com.grass.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 23:15
 * @Description
 **/
public class ValideUtils {

    public static boolean validateEmail(String src) {
        if (src != null)
            return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE).
                    matcher(src.trim()).find();
        else
            return false;
    }

    public static boolean validateMobile(String src) {
        if (StringUtils.isNotBlank(src)) {
            return Pattern.compile("^\\d{11}$", Pattern.CASE_INSENSITIVE).
                    matcher(src.trim()).find();
        } else {
            return false;
        }
    }

}
