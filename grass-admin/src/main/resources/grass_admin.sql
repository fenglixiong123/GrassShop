/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : grass_admin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-08 15:56:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `username` varchar(64) NOT NULL COMMENT '用户名',
                       `password` varchar(64) NOT NULL COMMENT '密码',
                       `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
                       `icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
                       `phone` varchar(20) DEFAULT NULL COMMENT '电话',
                       `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
                       `sex` int(11) DEFAULT '1' COMMENT '性别1男2女',
                       `address` varchar(255) DEFAULT NULL,
                       `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                       `status` int(11) DEFAULT '0' COMMENT '状态0不可用1正常',
                       `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                       `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456', 'admin123', '1', '18817311413', 'fenglixiong123@163.com', '1', '上海市徐汇区凯比努166', '超级管理员', '1', '2019-06-08 15:14:54', '2019-06-08 15:14:54');
INSERT INTO `admin` VALUES ('3', 'jack', '123456', 'jack123', '5', '18871455896', 'jack@163.com', '1', '上海市青浦区徐泾镇人民政府', '2', '1', '2019-06-07 03:42:18', '2019-06-07 18:08:51');
INSERT INTO `admin` VALUES ('4', 'marry', '123456', 'marry123', '333', '18817311456', 'marry123@163.com', '0', '上海市徐汇区凯滨路166号', '外籍美术家', '1', '2019-06-07 12:57:09', '2019-06-07 12:57:09');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `admin_id` bigint(20) NOT NULL COMMENT '用户ID',
                            `role_id` int(11) NOT NULL COMMENT '角色ID',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '1', '1');
INSERT INTO `admin_role` VALUES ('2', '3', '2');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `title` varchar(64) DEFAULT NULL COMMENT '菜单名称',
                      `parent_id` int(11) DEFAULT NULL COMMENT '父级菜单',
                      `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
                      `icon` varchar(64) DEFAULT NULL COMMENT '菜单图标代码',
                      `order` int(11) DEFAULT NULL COMMENT '菜单排序',
                      `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '主页', '0', '/home', 'el-icon-house', '1', '主页');
INSERT INTO `menu` VALUES ('2', '相册', '1', '/home/picture', 'el-icon-apple', '2', '相册');
INSERT INTO `menu` VALUES ('3', '时光', '1', '/home/time', 'el-icon-apple', '3', '时光');
INSERT INTO `menu` VALUES ('5', '指南', '1', '/home/guide', 'el-icon-apple', '5', '指南');
INSERT INTO `menu` VALUES ('6', '设置', '1', '/home/setting', 'el-icon-setting', '666', '设置');
INSERT INTO `menu` VALUES ('7', '新闻', '0', '/news', 'el-icon-suitcase', null, '新闻');
INSERT INTO `menu` VALUES ('8', '中国新闻', '7', '/news/china', 'el-icon-apple', null, '中国新闻');
INSERT INTO `menu` VALUES ('9', '美国新闻', '7', '/news/america', 'el-icon-apple', null, '美国新闻');
INSERT INTO `menu` VALUES ('10', '股票', '0', '/stock', 'el-icon-setting', null, '股票');
INSERT INTO `menu` VALUES ('11', '上海股票', '10', '/stock/shanghai', 'el-icon-apple', null, '上海股票');
INSERT INTO `menu` VALUES ('12', '深圳股票', '10', '/stock/shenzhen', 'el-icon-apple', null, '深圳股票');
INSERT INTO `menu` VALUES ('13', '中国财经新闻', '8', '/news/china/caijing', 'el-icon-apple', null, '中国财经新闻');
INSERT INTO `menu` VALUES ('14', '上海新三板', '11', '/stock/shanghai/three', 'el-icon-apple', null, '上海新三板');
INSERT INTO `menu` VALUES ('16', '用户管理', '0', '/user', 'el-icon-s-custom', null, '用户管理');
INSERT INTO `menu` VALUES ('17', '控制台用户管理', '16', '/user/admin', 'el-icon-s-custom', null, '控制台用户管理');
INSERT INTO `menu` VALUES ('18', '角色管理', '0', '/role', 'el-icon-user', null, '角色管理');
INSERT INTO `menu` VALUES ('19', '控制台角色管理', '18', '/role/admin', 'el-icon-apple', null, '控制台角色管理');
INSERT INTO `menu` VALUES ('20', '菜单管理', '0', '/menu', 'el-icon-s-operation', null, '菜单管理');
INSERT INTO `menu` VALUES ('21', '控制台菜单管理', '20', '/menu/admin', 'el-icon-apple', null, '控制台菜单管理');
INSERT INTO `menu` VALUES ('22', '权限管理', '0', '/power', 'el-icon-s-tools', null, '权限管理');
INSERT INTO `menu` VALUES ('23', '控制台权限管理', '22', '/power/admin', 'el-icon-apple', null, '控制台权限管理');
INSERT INTO `menu` VALUES ('24', '美国财经新闻', '9', '/amerac/news', '3333', '6', '2222');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `title` varchar(64) DEFAULT NULL COMMENT '权限名称',
                       `path` varchar(255) DEFAULT NULL COMMENT '权限路径',
                       `method` varchar(16) DEFAULT NULL COMMENT 'GET/POST/PUT/DELETE',
                       `parent_id` int(11) DEFAULT NULL COMMENT '展示用的',
                       `remark` varchar(255) DEFAULT NULL COMMENT '权限备注',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '登录管理', '/console/admin', 'GET', '0', '登录管理');
INSERT INTO `power` VALUES ('2', '用户登录', '/console/admin/login', 'POST', '1', '用户登录');
INSERT INTO `power` VALUES ('3', '用户退出', '/console/admin/logout', 'POST', '1', '用户退出');
INSERT INTO `power` VALUES ('4', '获取用户信息', '/console/admin/userInfo', 'GET', '1', '获取用户信息');
INSERT INTO `power` VALUES ('8', '用户管理', '/console/admin', 'GET', '0', '控制台用户管理');
INSERT INTO `power` VALUES ('9', '角色管理', '/console/admin/role', 'GET', '0', '控制台角色管理');
INSERT INTO `power` VALUES ('10', '菜单管理', '/console/admin/menu', 'GET', '0', '控制台菜单管理');
INSERT INTO `power` VALUES ('11', '权限管理', '/console/admin/power', 'GET', '0', '控制台权限管理');
INSERT INTO `power` VALUES ('12', '新增角色', '/console/admin/role', 'POST', '9', '新增角色');
INSERT INTO `power` VALUES ('13', '新增权限', '/console/admin/power', 'POST', '11', '新增权限');
INSERT INTO `power` VALUES ('15', '新增用户', '/console/admin', 'POST', '8', '新增用户');
INSERT INTO `power` VALUES ('16', '更新用户', '/console/admin', 'PUT', '8', '更新用户');
INSERT INTO `power` VALUES ('17', '删除用户', '/console/admin', 'DELETE', '8', '删除用户');
INSERT INTO `power` VALUES ('18', '用户列表分页', '/console/admin/list', 'POST', '8', '用户列表分页');
INSERT INTO `power` VALUES ('19', '根据用户ID查询角色', '/console/admin/findPossessRoleByAdminId', 'GET', '8', '根据用户ID查询角色');
INSERT INTO `power` VALUES ('20', '分配角色给用户', '/console/admin/assignRoleToAdmin', 'POST', '8', '分配角色给用户');
INSERT INTO `power` VALUES ('21', '修改角色', '/console/admin/role', 'PUT', '9', '修改角色');
INSERT INTO `power` VALUES ('22', '删除角色', '/console/admin/role', 'DELETE', '9', '删除角色');
INSERT INTO `power` VALUES ('23', '角色列表分页', '/console/admin/role/list', 'POST', '9', '角色列表分页');
INSERT INTO `power` VALUES ('24', '通过角色ID查询菜单', '/console/admin/role/findPossessMenuByRoleId', 'GET', '9', '通过角色ID查询拥有的菜单');
INSERT INTO `power` VALUES ('25', '分配菜单给角色', '/console/admin/role/assignMenuToRole', 'POST', '9', '分配菜单给角色');
INSERT INTO `power` VALUES ('26', '通过角色ID查询权限', '/console/admin/role/findPossessPowerByRoleId', 'GET', '9', '通过角色ID查询拥有的权限树');
INSERT INTO `power` VALUES ('27', '分配权限给角色', '/console/admin/role/assignPowerToRole', 'POST', '9', '分配权限给角色');
INSERT INTO `power` VALUES ('28', '修改权限', '/console/admin/power', 'PUT', '11', '修改权限');
INSERT INTO `power` VALUES ('29', '删除权限', '/console/admin/power', 'DELETE', '11', '删除权限');
INSERT INTO `power` VALUES ('30', '树形权限列表', '/console/admin/power/tree', 'GET', '11', '树形权限列表');
INSERT INTO `power` VALUES ('31', '新增菜单', '/console/admin/menu', 'POST', '10', '新增菜单');
INSERT INTO `power` VALUES ('32', '修改菜单', '/console/admin/menu', 'PUT', '10', '修改菜单');
INSERT INTO `power` VALUES ('33', '删除菜单', '/console/admin/menu', 'DELETE', '10', '删除菜单');
INSERT INTO `power` VALUES ('34', '树形菜单列表', '/console/admin/menu/tree', 'GET', '10', '树形菜单列表');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `title` varchar(64) NOT NULL COMMENT '角色名称',
                      `remark` varchar(255) DEFAULT NULL COMMENT '角色备注',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理所有页面');
INSERT INTO `role` VALUES ('2', '校长', '管理学校一切事物');
INSERT INTO `role` VALUES ('3', '教导', '管理阶级');
INSERT INTO `role` VALUES ('4', '老师', '管理教学');
INSERT INTO `role` VALUES ('5', '班长', '学习管理');
INSERT INTO `role` VALUES ('6', '组长', '管理小组');
INSERT INTO `role` VALUES ('7', '学习委员', '管理学习的');
INSERT INTO `role` VALUES ('8', '课代表', '代表某门功课的');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `role_id` int(11) DEFAULT NULL,
                           `menu_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '1', '2');
INSERT INTO `role_menu` VALUES ('3', '1', '3');
INSERT INTO `role_menu` VALUES ('5', '1', '5');
INSERT INTO `role_menu` VALUES ('6', '1', '6');
INSERT INTO `role_menu` VALUES ('7', '1', '7');
INSERT INTO `role_menu` VALUES ('8', '1', '8');
INSERT INTO `role_menu` VALUES ('9', '1', '9');
INSERT INTO `role_menu` VALUES ('10', '1', '10');
INSERT INTO `role_menu` VALUES ('11', '1', '11');
INSERT INTO `role_menu` VALUES ('12', '1', '12');
INSERT INTO `role_menu` VALUES ('13', '1', '13');
INSERT INTO `role_menu` VALUES ('14', '1', '14');
INSERT INTO `role_menu` VALUES ('16', '1', '16');
INSERT INTO `role_menu` VALUES ('17', '1', '17');
INSERT INTO `role_menu` VALUES ('18', '1', '18');
INSERT INTO `role_menu` VALUES ('19', '1', '19');
INSERT INTO `role_menu` VALUES ('20', '1', '20');
INSERT INTO `role_menu` VALUES ('21', '1', '21');
INSERT INTO `role_menu` VALUES ('22', '1', '22');
INSERT INTO `role_menu` VALUES ('23', '1', '23');
INSERT INTO `role_menu` VALUES ('25', '2', '2');
INSERT INTO `role_menu` VALUES ('28', '2', '5');
INSERT INTO `role_menu` VALUES ('40', '3', '7');
INSERT INTO `role_menu` VALUES ('41', '3', '8');
INSERT INTO `role_menu` VALUES ('42', '3', '13');
INSERT INTO `role_menu` VALUES ('43', '3', '9');
INSERT INTO `role_menu` VALUES ('44', '3', '24');
INSERT INTO `role_menu` VALUES ('45', '3', '10');
INSERT INTO `role_menu` VALUES ('46', '3', '11');
INSERT INTO `role_menu` VALUES ('47', '3', '14');
INSERT INTO `role_menu` VALUES ('52', '1', '24');
INSERT INTO `role_menu` VALUES ('53', '2', '1');
INSERT INTO `role_menu` VALUES ('54', '2', '3');
INSERT INTO `role_menu` VALUES ('56', '2', '6');
INSERT INTO `role_menu` VALUES ('57', '2', '16');
INSERT INTO `role_menu` VALUES ('58', '2', '17');
INSERT INTO `role_menu` VALUES ('59', '2', '18');
INSERT INTO `role_menu` VALUES ('60', '2', '19');
INSERT INTO `role_menu` VALUES ('61', '2', '20');
INSERT INTO `role_menu` VALUES ('62', '2', '21');
INSERT INTO `role_menu` VALUES ('63', '2', '22');
INSERT INTO `role_menu` VALUES ('64', '2', '23');

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `role_id` int(11) NOT NULL COMMENT '角色ID',
                            `power_id` int(11) NOT NULL COMMENT '权限ID',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_power
-- ----------------------------
INSERT INTO `role_power` VALUES ('1', '1', '1');
INSERT INTO `role_power` VALUES ('2', '1', '2');
INSERT INTO `role_power` VALUES ('3', '1', '3');
INSERT INTO `role_power` VALUES ('4', '1', '4');
INSERT INTO `role_power` VALUES ('7', '3', '8');
INSERT INTO `role_power` VALUES ('9', '3', '9');
INSERT INTO `role_power` VALUES ('10', '3', '12');
INSERT INTO `role_power` VALUES ('11', '3', '10');
INSERT INTO `role_power` VALUES ('12', '3', '13');
INSERT INTO `role_power` VALUES ('14', '3', '1');
INSERT INTO `role_power` VALUES ('15', '1', '8');
INSERT INTO `role_power` VALUES ('17', '1', '9');
INSERT INTO `role_power` VALUES ('18', '1', '12');
INSERT INTO `role_power` VALUES ('19', '1', '10');
INSERT INTO `role_power` VALUES ('20', '1', '13');
INSERT INTO `role_power` VALUES ('21', '2', '1');
INSERT INTO `role_power` VALUES ('22', '2', '2');
INSERT INTO `role_power` VALUES ('23', '2', '3');
INSERT INTO `role_power` VALUES ('24', '2', '4');
INSERT INTO `role_power` VALUES ('27', '1', '15');
INSERT INTO `role_power` VALUES ('28', '1', '16');
INSERT INTO `role_power` VALUES ('29', '1', '17');
INSERT INTO `role_power` VALUES ('30', '1', '18');
INSERT INTO `role_power` VALUES ('31', '1', '19');
INSERT INTO `role_power` VALUES ('32', '1', '20');
INSERT INTO `role_power` VALUES ('33', '1', '21');
INSERT INTO `role_power` VALUES ('34', '1', '22');
INSERT INTO `role_power` VALUES ('35', '1', '23');
INSERT INTO `role_power` VALUES ('36', '1', '24');
INSERT INTO `role_power` VALUES ('37', '1', '25');
INSERT INTO `role_power` VALUES ('38', '1', '26');
INSERT INTO `role_power` VALUES ('39', '1', '27');
INSERT INTO `role_power` VALUES ('40', '1', '31');
INSERT INTO `role_power` VALUES ('41', '1', '32');
INSERT INTO `role_power` VALUES ('42', '1', '33');
INSERT INTO `role_power` VALUES ('43', '1', '34');
INSERT INTO `role_power` VALUES ('44', '1', '11');
INSERT INTO `role_power` VALUES ('45', '1', '28');
INSERT INTO `role_power` VALUES ('46', '1', '29');
INSERT INTO `role_power` VALUES ('47', '1', '30');
INSERT INTO `role_power` VALUES ('48', '2', '18');
INSERT INTO `role_power` VALUES ('49', '2', '23');
INSERT INTO `role_power` VALUES ('50', '2', '34');
INSERT INTO `role_power` VALUES ('51', '2', '30');
