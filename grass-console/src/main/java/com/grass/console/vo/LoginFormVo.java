package com.grass.console.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Fenglixiong
 * @Create 2019/5/23 0:19
 * @Description
 **/
@Data
public class LoginFormVo {

    @NotBlank(message = "username is blank")
    private String username;

    @NotBlank(message = "password is blank")
    private String password;

}
