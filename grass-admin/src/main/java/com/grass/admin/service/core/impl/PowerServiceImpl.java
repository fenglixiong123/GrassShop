package com.grass.admin.service.core.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.PowerDao;
import com.grass.admin.model.Power;
import com.grass.admin.model.PowerExample;
import com.grass.admin.service.relation.AdminRoleService;
import com.grass.admin.service.core.PowerService;
import com.grass.admin.service.relation.RolePowerService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.PowerVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.CommonUtils;
import com.grass.web.exception.element.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:15
 * @Description
 **/
@Slf4j
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RolePowerService rolePowerService;
    @Autowired
    private PowerDao powerDao;

    /**
     * 获取所有权限列表
     * @return
     */
    public List<PowerVo> findList(){
        List<Power> powers = powerDao.selectByExample(null);
        return CopyUtil.copyPowerEntity(powers);
    }

    /**
     * 通过adminId获取权限列表
     * @param adminId
     * @return
     */
    public List<PowerVo> findListByAdminId(Long adminId){
        //1.首先获得用户角色ids
        List<Integer> roleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        //2.获得用户权限ids
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleIds(roleIds);
        //3.获取用户权限
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过roleId获取权限列表
     * @param roleId
     * @return
     */
    public List<PowerVo> findListByRoleId(Integer roleId){
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleId(roleId);
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过roleIds获取权限列表
     * @param roleIds
     * @return
     */
    public List<PowerVo> findListByRoleIds(List<Integer> roleIds){
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleIds(roleIds);
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过powerIds获取权限列表
     * @param powerIds
     * @return
     */
    public List<PowerVo> findListByPowerIds(List<Integer> powerIds){
        if(CommonUtils.isEmpty(powerIds)){
            return null;
        }
        PowerExample example = new PowerExample();
        example.createCriteria().andIdIn(powerIds);
        List<Power> powers = powerDao.selectByExample(example);
        return CopyUtil.copyPowerEntity(powers);
    }


    @Override
    public PowerVo get(Integer id) {
        return CopyUtil.copyPowerEntity(powerDao.selectByPrimaryKey(id));
    }

    @Override
    public Integer add(PowerVo powerVo) {
        Power power = new Power();
        BeanUtils.copyProperties(powerVo,power);
        powerDao.insertSelective(power);
        return power.getId();
    }

    @Override
    public int update(PowerVo powerVo) {
        if(powerVo.getId()==null){
            throw new ParamException("更新ID为空！");
        }
        Power power = new Power();
        BeanUtils.copyProperties(powerVo,power);
        return powerDao.updateByPrimaryKeySelective(power);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        int res = powerDao.deleteByPrimaryKey(id);
        //删除角色权限关系
        rolePowerService.deleteByPowerId(id);
        return res;
    }

    @Override
    public PageResult<PowerVo> list(PageQuery<PowerVo> pageQuery) {
        Page<Power> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Power> powerList = queryList(pageQuery);
        PageInfo<Power> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, CopyUtil.copyPowerEntity(powerList));
    }

    @Override
    public List<PowerVo> listAll(PageQuery<PowerVo> pageQuery) {

        return CopyUtil.copyPowerEntity(queryList(pageQuery));
    }

    private List<Power> queryList(PageQuery<PowerVo> pageQuery){
        PowerExample example = new PowerExample();
        PowerVo queryVo = pageQuery.getEntity();
        if(queryVo!=null){
            PowerExample.Criteria criteria = example.createCriteria();
            if(queryVo.getId()!=null){
                criteria.andIdEqualTo(queryVo.getId());
            }
            if(StringUtils.isNotBlank(queryVo.getTitle())){
                criteria.andTitleLike("%"+queryVo.getTitle()+"%");
            }
        }
        return powerDao.selectByExample(example);
    }

}
