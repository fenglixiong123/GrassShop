package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/6/8 13:44
 * @Description 登录成功后返回的数据
 **/
@Data
@NoArgsConstructor
public class LoginResult {

    private String token;

    private AdminVo user;

    private List<MenuVo> menuList;

    public LoginResult(String token, AdminVo user, List<MenuVo> menuList) {
        this.token = token;
        this.user = user;
        this.menuList = menuList;
    }
}
