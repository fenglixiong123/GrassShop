package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/6/6 15:11
 * @Description: 某个用户拥有的角色和所有的角色集
 */
@Data
@NoArgsConstructor
public class PossessRole {

    private List<RoleVo> allRoles;

    private List<RoleVo> hasRoles;

    public PossessRole(List<RoleVo> allRoles, List<RoleVo> hasRoles) {
        this.allRoles = allRoles;
        this.hasRoles = hasRoles;
    }
}
