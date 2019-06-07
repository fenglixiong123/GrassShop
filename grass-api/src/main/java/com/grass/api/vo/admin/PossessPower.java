package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/6/6 16:39
 * @Description: 某个角色拥有的权限和所有权限
 */
@Data
@NoArgsConstructor
public class PossessPower {

    private List<PowerVo> allPowers;

    private List<Integer> hasPowers;

    public PossessPower(List<PowerVo> allPowers, List<Integer> hasPowers) {
        this.allPowers = allPowers;
        this.hasPowers = hasPowers;
    }
}
