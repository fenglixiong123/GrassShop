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

    public static AddDeleteVo resolve(List<Integer> oldList,List<Integer> newList){
        if(newList==null){
            return new AddDeleteVo(null,oldList);
        }
        if(oldList==null){
            return new AddDeleteVo(newList,null);
        }
        List<Integer> needDelete = new ArrayList<>();
        List<Integer> needAdd = new ArrayList<>(newList);
        for (int i = 0; i < oldList.size(); i++) {
            Integer temp = oldList.get(i);
            if(needAdd.contains(temp)){
                needAdd.remove(temp);
            }else {
                needDelete.add(temp);
            }
        }
        return new AddDeleteVo(needAdd,needDelete);
    }

}
