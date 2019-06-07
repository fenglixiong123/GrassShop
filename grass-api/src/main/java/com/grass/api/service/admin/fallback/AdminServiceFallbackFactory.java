package com.grass.api.service.admin.fallback;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.*;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:02
 * @Description 服务降级，客户端层面，客户端发现服务停止就会采用默认返回值
 **/
@Slf4j
@Component
public class AdminServiceFallbackFactory implements FallbackFactory<IAdminService> {

    public IAdminService create(Throwable throwable) {
        return new IAdminService() {

            public AdminVo getAdmin(Long id) {
                return new AdminVo(-99L,"default");
            }

            public Long addAdmin(AdminVo adminVo) {
                return -99L;
            }

            public int updateAdmin(AdminVo adminVo) {
                return -99;
            }

            public int deleteAdmin(Long id) {
                return -99;
            }

            public PageResult<AdminVo> listPageAdmin(@RequestBody(required = false) PageQuery<AdminVo> pageQuery) {
                return new PageResult<AdminVo>();
            }

            public AdminVo getAdminByUsernameAndPassword(String username, String password) {
                return new AdminVo(-99L,"default");
            }

            public PossessRole findPossessRoleByAdminId(Long id) {
                return null;
            }

            public void assignRoleToAdmin(List<Integer> roleIds, Long adminId) {

            }

            public RoleVo getRole(Integer id) {
                return null;
            }

            public Integer addRole(RoleVo roleVo) {
                return null;
            }

            public int updateRole(RoleVo roleVo) {
                return 0;
            }

            public int deleteRole(Integer id) {
                return 0;
            }

            public PageResult<RoleVo> listPageRole(PageQuery<RoleVo> pageQuery) {
                return null;
            }

            public PossessMenu findPossessMenuByRoleId(Integer id) {
                return null;
            }

            public void assignMenuToRole(List<Integer> menuIds, Integer roleId) {

            }

            public PossessPower findPossessPowerByRoleId(Integer id) {
                return null;
            }

            public void assignPowerToRole(List<Integer> powerIds, Integer roleId) {

            }

            public MenuVo getMenu(Integer id) {
                return null;
            }

            public Integer addMenu(MenuVo menuVo) {
                return null;
            }

            public int updateMenu(MenuVo menuVo) {
                return 0;
            }

            public int deleteMenu(Integer id) {
                return 0;
            }

            public PageResult<MenuVo> listPageMenu(PageQuery<MenuVo> pageQuery) {
                return null;
            }

            public List<MenuVo> treeMenu() {
                return null;
            }

            public List<MenuVo> findMenuListByAdminId(Long id) {
                return null;
            }

            public List<MenuVo> findMenuTreeByAdminId(Long id) {
                return null;
            }

            public PowerVo getPower(Integer id) {
                return null;
            }

            public Integer addPower(PowerVo powerVo) {
                return null;
            }

            public int updatePower(PowerVo powerVo) {
                return 0;
            }

            public int deletePower(Integer id) {
                return 0;
            }

            public PageResult<PowerVo> listPagePower(PageQuery<PowerVo> pageQuery) {
                return null;
            }

            public List<PowerVo> treePower() {
                return null;
            }

            public List<PowerVo> findPowerListByAdminId(Long id) {
                return null;
            }

            public List<PowerVo> findPowerTreeByAdminId(Long id) {
                return null;
            }


        };
    }

}
