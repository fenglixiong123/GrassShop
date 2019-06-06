package com.grass.admin.utils;

import com.grass.admin.vo.AddDeleteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/6/6 20:11
 * @Description:
 */
public class AssignUtil {

    public static AddDeleteVo resolve(List<Integer> ownList,List<Integer> newList){
        List<Integer> needDelete = new ArrayList<>();
        List<Integer> needAdd = new ArrayList<>(newList);
        for (int i = 0; i < ownList.size(); i++) {
            Integer temp = ownList.get(i);
            if(needAdd.contains(temp)){
                needAdd.remove(temp);
            }else {
                needDelete.add(temp);
            }
        }
        return new AddDeleteVo();
    }

}
