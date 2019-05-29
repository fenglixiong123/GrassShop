package com.grass.admin.utils;

import com.grass.api.vo.admin.MenuVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.common.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 23:31
 * @Description
 **/
public class ConvertTreeUtil {

    /**
     * 将列表菜单转化成树
     */
    public static List<MenuVo> listToTreeMenu(List<MenuVo> menuVoList){
        if(CommonUtils.isEmpty(menuVoList)){
            return null;
        }
        List<MenuVo> treeList = new ArrayList<>();
        for (MenuVo menuVo : menuVoList){
            if(menuVo.getParentId() == 0){
                treeList.add(menuVo);
            }
        }
        for (MenuVo menuVo : menuVoList){
            getMenuChildren(treeList,menuVo);
        }
        return treeList;
    }

    private static void getMenuChildren(List<MenuVo> treeList,MenuVo node){
        for (MenuVo rootNode : treeList){
            if(node.getParentId().equals(rootNode.getId())){
                if(rootNode.getChildren()==null){
                    rootNode.setChildren(new ArrayList<>());
                }
                rootNode.getChildren().add(node);
            }
            if(rootNode.getChildren()!=null){
                getMenuChildren(rootNode.getChildren(),node);
            }
        }
    }

    /**
     * 将列表权限转化成树
     */
    public static List<PowerVo> listToTreePower(List<PowerVo> powerVoList){
        if(CommonUtils.isEmpty(powerVoList)){
            return null;
        }
        List<PowerVo> treeList = new ArrayList<>();
        for (PowerVo powerVo : powerVoList){
            if(powerVo.getParentId() == 0){
                treeList.add(powerVo);
            }
        }
        for (PowerVo powerVo : powerVoList){
            getPowerChildren(treeList,powerVo);
        }
        return treeList;
    }

    private static void getPowerChildren(List<PowerVo> treeList,PowerVo node){
        for (PowerVo rootNode : treeList){
            if(node.getParentId().equals(rootNode.getId())){
                if(rootNode.getChildren()==null){
                    rootNode.setChildren(new ArrayList<>());
                }
                rootNode.getChildren().add(node);
            }
            if(rootNode.getChildren()!=null){
                getPowerChildren(rootNode.getChildren(),node);
            }
        }
    }

}
