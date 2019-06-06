package com.grass.admin.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/6/6 20:10
 * @Description:
 */
@Data
@NoArgsConstructor
public class AddDeleteVo {

    private List<Integer> needAdd;

    private List<Integer> needDelete;

    public AddDeleteVo(List<Integer> needAdd, List<Integer> needDelete) {
        this.needAdd = needAdd;
        this.needDelete = needDelete;
    }
}
