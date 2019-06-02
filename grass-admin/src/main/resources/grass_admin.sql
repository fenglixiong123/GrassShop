/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : grass_admin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-03 00:23:06
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin123', 'fenglixiong', null, '18817311413', 'fenglixiong123@163.com', '1', '上海市松江区江川二村南区38号501', '管理员', '1', '2019-05-19 22:46:03', '2019-05-19 22:46:03');
INSERT INTO `admin` VALUES ('2', 'jack', 'jack123', 'jack', '', '18817311413', 'fenglixiong123@163.com', '1', '上海市松江区江川二村南区38号501', '管理员', '1', '2019-05-19 22:46:03', '2019-05-19 22:46:03');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '主页', '0', '/home', null, null, '主页');
INSERT INTO `menu` VALUES ('2', '相册', '1', '/home/picture', null, null, '相册');
INSERT INTO `menu` VALUES ('3', '时光', '1', '/home/time', null, null, '时光');
INSERT INTO `menu` VALUES ('4', '用户', '1', '/home/admin', null, null, '用户');
INSERT INTO `menu` VALUES ('5', '指南', '1', '/home/guide', null, null, '指南');
INSERT INTO `menu` VALUES ('6', '设置', '1', '/home/setting', null, null, '设置');
INSERT INTO `menu` VALUES ('7', '新闻', '0', '/news', null, null, '新闻');
INSERT INTO `menu` VALUES ('8', '中国新闻', '7', '/news/china', null, null, '中国新闻');
INSERT INTO `menu` VALUES ('9', '美国新闻', '7', '/news/america', null, null, '美国新闻');
INSERT INTO `menu` VALUES ('10', '股票', '0', '/stock', null, null, '股票');
INSERT INTO `menu` VALUES ('11', '上海股票', '10', '/stock/shanghai', null, null, '上海股票');
INSERT INTO `menu` VALUES ('12', '深圳股票', '10', '/stock/shenzhen', null, null, '深圳股票');
INSERT INTO `menu` VALUES ('13', '中国财经新闻', '8', '/news/china/caijing', null, null, '中国财经新闻');
INSERT INTO `menu` VALUES ('14', '上海新三板', '11', '/stock/shanghai/three', null, null, '上海新三板');
INSERT INTO `menu` VALUES ('15', '测试页面', '0', '/test', null, null, '测试页面');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '主页根', '/home', null, '0', '主页根');
INSERT INTO `power` VALUES ('2', '主页', '/home/house', null, '1', '主页面');
INSERT INTO `power` VALUES ('3', '时光', '/home/time', null, '1', '时光页面');
INSERT INTO `power` VALUES ('4', '用户', '/home/admin', null, '1', '用户页面');
INSERT INTO `power` VALUES ('5', '指南', '/home/guide', null, '1', '指南页面');
INSERT INTO `power` VALUES ('6', '设置', '/home/setting', null, '1', '设置页面');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '角色备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理所有页面');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '1', '2');
INSERT INTO `role_menu` VALUES ('3', '1', '3');
INSERT INTO `role_menu` VALUES ('4', '1', '4');
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
INSERT INTO `role_menu` VALUES ('15', '1', '15');

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `power_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_power
-- ----------------------------
INSERT INTO `role_power` VALUES ('1', '1', '1');
INSERT INTO `role_power` VALUES ('2', '1', '2');
INSERT INTO `role_power` VALUES ('3', '1', '3');
INSERT INTO `role_power` VALUES ('4', '1', '4');
INSERT INTO `role_power` VALUES ('5', '1', '5');
INSERT INTO `role_power` VALUES ('6', '1', '6');
