/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : payroll-system

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 27/08/2021 22:08:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'PK，部门id',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0=正常(默认)，1=停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0=存在(默认)，2=删除）',
  `creater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (2, '人力资源部', 'root1', '13788395907', '1982989137@qq.com', '0', '0', 'root', '2021-07-29 09:25:21', '1', '2021-08-20 00:37:55');
INSERT INTO `dept` VALUES (3, '财务部', 'root11', '12478933819', 'hcsaio@qq.com', '0', '0', '1', '2021-08-19 17:26:31', '1', '2021-08-19 17:27:13');
INSERT INTO `dept` VALUES (4, '市场部', '张三', '13788395907', 'hcsaio@qq.com', '0', '0', '1', '2021-08-20 00:27:11', '', NULL);
INSERT INTO `dept` VALUES (5, '总裁办公室', '王五', '18952328733', '28316892@fox.com', '0', '0', '1', '2021-08-25 14:06:55', '', NULL);
INSERT INTO `dept` VALUES (6, 'test', 'teset', '13788395907', '1982989137@qq.com', '0', '0', '1', '2021-08-25 14:07:25', '', NULL);
INSERT INTO `dept` VALUES (7, '研发部', '李四', '13489124719', '4819259@163.com', '0', '0', '1', '2021-08-25 14:06:04', '', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `menu_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'M' COMMENT '菜单类型（\'M\'=菜单(默认)，\'B\'=按钮）',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单id',
  `href` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `target` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '_self' COMMENT '打开方式（\"_self\"=打开新页面）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0=显示(默认)，1=隐藏）',
  `creater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1119 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '主页', 'M', 1, 0, 'system/welcome/welcome', '_self', 'system:welcome:view', 'fa fa-home', '0', 'root', '2021-07-29 23:28:32', '', NULL, '主页');
INSERT INTO `menu` VALUES (2, '用户管理', 'M', 2, 0, 'system/user/user', '_self', 'system:user:view', 'fa fa-user-o', '0', 'root', '2021-07-29 23:28:32', '', NULL, '用户管理菜单');
INSERT INTO `menu` VALUES (3, '角色管理', 'M', 3, 0, 'system/role/role', '_self', 'system:role:view', 'fa fa-user-secret', '0', 'root', '2021-07-29 23:29:16', '', NULL, '角色管理菜单');
INSERT INTO `menu` VALUES (4, '部门管理', 'M', 4, 0, 'system/dept/dept', '_self', 'system:dept:view', 'fa fa-outdent', '0', 'root', '2021-07-29 23:29:55', '', NULL, '部门管理菜单');
INSERT INTO `menu` VALUES (5, '薪资管理', 'M', 5, 0, '', '_self', 'system:salary:view', 'fa fa-jpy', '0', 'root', '2021-08-20 13:19:47', '', NULL, '薪资管理菜单');
INSERT INTO `menu` VALUES (6, '奖惩录入', 'M', 1, 5, 'system/salary/input/input', '_self', 'system:salary:input:view', 'fa fa-pencil-square-o', '0', 'root', '2021-08-20 13:24:37', '', NULL, '奖惩录入');
INSERT INTO `menu` VALUES (7, '薪资发放', 'M', 2, 5, 'system/salary/pay/pay', '_self', 'system:salary:pay:view', 'fa fa-cc-paypal', '0', 'root', '2021-08-20 14:40:13', '', NULL, '薪资发放');
INSERT INTO `menu` VALUES (8, '薪资参数', 'M', 3, 5, 'system/salary/config/config', '_self', 'system:salary:config:view', 'fa fa-cog', '0', 'root', '2021-08-20 14:41:12', '', NULL, '薪资参数');
INSERT INTO `menu` VALUES (9, '薪资查看', 'M', 6, 0, '', '_self', 'system:watch:view', 'fa fa-eye', '0', 'root', '2021-08-24 17:17:05', '', NULL, '薪资查看');
INSERT INTO `menu` VALUES (10, '部门薪资', 'M', 7, 9, 'system/watch/dept/deptSalary', '_self', 'system:watch:dept:view', 'fa fa-outdent', '0', 'root', '2021-08-24 17:29:03', '', NULL, '部门薪资');
INSERT INTO `menu` VALUES (11, '公司薪资', 'M', 8, 9, 'system/watch/all/allSalary', '_self', 'system:watch:all:view', 'fa fa-users', '0', 'root', '2021-08-24 17:36:26', '', NULL, '公司薪资');
INSERT INTO `menu` VALUES (12, '薪资统计', 'M', 9, 0, 'system/total/total', '_self', 'system:total:view', 'fa fa-pie-chart', '0', 'root', '2021-08-20 23:24:37', '', NULL, '薪资统计');
INSERT INTO `menu` VALUES (100, '用户查询', 'B', 1, 2, '#', '_self', 'system:user:select', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '用户查询');
INSERT INTO `menu` VALUES (101, '添加用户', 'B', 2, 2, '#', '_self', 'system:user:insert', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '添加用户');
INSERT INTO `menu` VALUES (102, '修改用户', 'B', 3, 2, '#', '_self', 'system:user:update', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '修改用户');
INSERT INTO `menu` VALUES (103, '删除用户', 'B', 4, 2, '#', '_self', 'system:user:delete', '#', '0', 'root', '2021-07-31 13:36:21', '', '2021-07-31 13:41:24', '删除用户');
INSERT INTO `menu` VALUES (104, '导出用户', 'B', 5, 2, '#', '_self', 'system:user:export', '#', '0', 'root', '2021-07-31 13:44:20', '', '2021-07-31 13:44:10', '导出用户');
INSERT INTO `menu` VALUES (105, '导入用户', 'B', 6, 2, '#', '_self', 'system:user:import', '#', '0', 'root', '2021-07-31 13:44:23', '', '2021-07-31 13:44:13', '导入用户');
INSERT INTO `menu` VALUES (106, '重置密码', 'B', 7, 2, '#', '_self', 'system:user:reset', '#', '0', 'root', '2021-07-31 13:44:25', '', '2021-07-31 13:44:15', '重置密码');
INSERT INTO `menu` VALUES (107, '角色查询', 'B', 1, 3, '#', '_self', 'system:role:select', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '角色查询');
INSERT INTO `menu` VALUES (108, '添加角色', 'B', 2, 3, '#', '_self', 'system:role:insert', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '添加角色');
INSERT INTO `menu` VALUES (109, '修改角色', 'B', 3, 3, '#', '_self', 'system:role:update', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '修改角色');
INSERT INTO `menu` VALUES (110, '删除角色', 'B', 4, 3, '#', '_self', 'system:role:delete', '#', '0', 'root', '2021-07-31 13:36:21', '', '2021-07-31 13:41:24', '删除角色');
INSERT INTO `menu` VALUES (111, '导出角色', 'B', 5, 3, '#', '_self', 'system:role:export', '#', '0', 'root', '2021-07-31 13:44:20', '', '2021-07-31 13:44:10', '导出角色');
INSERT INTO `menu` VALUES (112, '部门查询', 'B', 1, 4, '#', '_self', 'system:dept:select', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '部门查询');
INSERT INTO `menu` VALUES (113, '添加部门', 'B', 2, 4, '#', '_self', 'system:dept:insert', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '添加部门');
INSERT INTO `menu` VALUES (114, '修改部门', 'B', 3, 4, '#', '_self', 'system:dept:update', '#', '0', 'root', '2021-07-31 13:36:21', '', NULL, '修改部门');
INSERT INTO `menu` VALUES (115, '删除部门', 'B', 4, 4, '#', '_self', 'system:dept:delete', '#', '0', 'root', '2021-07-31 13:36:21', '', '2021-07-31 13:41:24', '删除部门');
INSERT INTO `menu` VALUES (116, '导出部门', 'B', 5, 4, '#', '_self', 'system:dept:export', '#', '0', 'root', '2021-07-31 13:44:20', '', '2021-07-31 13:44:10', '导出部门');
INSERT INTO `menu` VALUES (117, '查询薪资配置', 'B', 1, 8, '#', '_self', 'system:salary:config:select', '#', '0', 'root', '2021-08-22 22:51:01', '', NULL, '查询薪资配置');
INSERT INTO `menu` VALUES (118, '导出薪资配置', 'B', 2, 8, '#', '_self', 'system:salary:config:export', '#', '0', 'root', '2021-08-22 22:57:32', '', NULL, '导出薪资配置');
INSERT INTO `menu` VALUES (119, '修改薪资配置', 'B', 3, 8, '#', '_self', 'system:salary:config:update', '#', '0', 'root', '2021-08-22 22:51:53', '', NULL, '修改薪资配置');
INSERT INTO `menu` VALUES (120, '查询奖惩', 'B', 1, 6, '#', '_self', 'system:salary:input:select', '#', '0', 'root', '2021-08-23 14:02:25', '', NULL, '查询奖惩');
INSERT INTO `menu` VALUES (121, '录入奖惩', 'B', 2, 6, '#', '_self', 'system:salary:input:update', '#', '0', 'root', '2021-08-23 14:37:47', '', NULL, '录入奖惩');
INSERT INTO `menu` VALUES (122, '导出奖惩', 'B', 3, 6, '#', '_self', 'system:salary:input:export', '#', '0', 'root', '2021-08-23 14:44:14', '', NULL, '导出奖惩');
INSERT INTO `menu` VALUES (123, '查询薪资', 'B', 1, 7, '#', '_self', 'system:salary:pay:select', '#', '0', 'root', '2021-08-24 11:28:32', '', NULL, '查询薪资');
INSERT INTO `menu` VALUES (124, '审核薪资', 'B', 2, 7, '#', '_self', 'system:salary:pay:update', '#', '0', 'root', '2021-08-24 11:29:35', '', NULL, '审核薪资');
INSERT INTO `menu` VALUES (125, '导出审核薪资', 'B', 3, 7, '#', '_self', 'system:salary:pay:export', '#', '0', 'root', '2021-08-24 11:30:57', '', NULL, '导出审核薪资');
INSERT INTO `menu` VALUES (126, '搜索部门薪资', 'B', 1, 10, '#', '_self', 'system:watch:dept:search', '#', '0', 'root', '2021-08-25 09:03:22', '', NULL, '搜索部门薪资');
INSERT INTO `menu` VALUES (127, '导出部门薪资', 'B', 2, 10, '#', '_self', 'system:watch:dept:export', '#', '0', 'root', '2021-08-25 09:04:45', '', NULL, '导出部门薪资');
INSERT INTO `menu` VALUES (128, '搜索公司薪资', 'B', 3, 11, '#', '_self', 'system:watch:all:search', '#', '0', 'root', '2021-08-25 13:37:36', '', NULL, '搜索公司薪资');
INSERT INTO `menu` VALUES (129, '导出公司薪资', 'B', 4, 11, '#', '_self', 'system:watch:all:export', '#', '0', 'root', '2021-08-25 13:38:19', '', NULL, '导出公司薪资');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'PK，角色id',
  `role_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色权限字符串',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '角色状态（0=正常(默认)，1=停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0=存在(默认)，2=删除）',
  `creater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'root', '系统管理员', '0', '0', 'root', '2021-07-26 17:08:57', '1', '2021-08-18 11:18:17', '系统管理员，拥有对系统的所有权限');
INSERT INTO `role` VALUES (2, 'ceo', '董事长', '0', '0', 'root', '2021-07-26 17:10:30', '1', '2021-08-25 13:48:57', '董事长负责管理公司重大决策');
INSERT INTO `role` VALUES (3, 'general_manager', '总经理', '0', '0', 'root', '2021-07-26 17:11:33', '1', '2021-08-27 17:25:34', '总经理负责管理部门经理的事项');
INSERT INTO `role` VALUES (4, 'department_manager', '部门经理', '0', '0', 'root', '2021-07-26 17:12:37', '1', '2021-08-27 17:27:09', '部门经理负责管理本部门的事项');
INSERT INTO `role` VALUES (5, 'hr', '人力资源工作人员', '0', '0', 'root', '2021-07-26 17:14:43', '1', '2021-08-27 17:24:49', '人力资源工作人员负责考勤和人员信息的变动');
INSERT INTO `role` VALUES (6, 'fd', '财务部门工作人员', '0', '0', 'root', '2021-07-26 17:16:16', '1', '2021-08-27 17:25:02', '财务部门工作人员负责人员奖惩录入');
INSERT INTO `role` VALUES (7, 'clerk', '普通职员', '0', '0', 'root', '2021-07-26 17:17:54', '1', '2021-08-27 17:25:18', '普通职员');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (1, 9);
INSERT INTO `role_menu` VALUES (1, 10);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 100);
INSERT INTO `role_menu` VALUES (1, 101);
INSERT INTO `role_menu` VALUES (1, 102);
INSERT INTO `role_menu` VALUES (1, 103);
INSERT INTO `role_menu` VALUES (1, 104);
INSERT INTO `role_menu` VALUES (1, 105);
INSERT INTO `role_menu` VALUES (1, 106);
INSERT INTO `role_menu` VALUES (1, 107);
INSERT INTO `role_menu` VALUES (1, 108);
INSERT INTO `role_menu` VALUES (1, 109);
INSERT INTO `role_menu` VALUES (1, 110);
INSERT INTO `role_menu` VALUES (1, 111);
INSERT INTO `role_menu` VALUES (1, 112);
INSERT INTO `role_menu` VALUES (1, 113);
INSERT INTO `role_menu` VALUES (1, 114);
INSERT INTO `role_menu` VALUES (1, 115);
INSERT INTO `role_menu` VALUES (1, 116);
INSERT INTO `role_menu` VALUES (1, 117);
INSERT INTO `role_menu` VALUES (1, 118);
INSERT INTO `role_menu` VALUES (1, 119);
INSERT INTO `role_menu` VALUES (1, 120);
INSERT INTO `role_menu` VALUES (1, 121);
INSERT INTO `role_menu` VALUES (1, 122);
INSERT INTO `role_menu` VALUES (1, 123);
INSERT INTO `role_menu` VALUES (1, 124);
INSERT INTO `role_menu` VALUES (1, 125);
INSERT INTO `role_menu` VALUES (1, 126);
INSERT INTO `role_menu` VALUES (1, 127);
INSERT INTO `role_menu` VALUES (1, 128);
INSERT INTO `role_menu` VALUES (1, 129);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 4);
INSERT INTO `role_menu` VALUES (2, 5);
INSERT INTO `role_menu` VALUES (2, 6);
INSERT INTO `role_menu` VALUES (2, 7);
INSERT INTO `role_menu` VALUES (2, 8);
INSERT INTO `role_menu` VALUES (2, 9);
INSERT INTO `role_menu` VALUES (2, 10);
INSERT INTO `role_menu` VALUES (2, 11);
INSERT INTO `role_menu` VALUES (2, 12);
INSERT INTO `role_menu` VALUES (2, 100);
INSERT INTO `role_menu` VALUES (2, 101);
INSERT INTO `role_menu` VALUES (2, 102);
INSERT INTO `role_menu` VALUES (2, 103);
INSERT INTO `role_menu` VALUES (2, 104);
INSERT INTO `role_menu` VALUES (2, 105);
INSERT INTO `role_menu` VALUES (2, 106);
INSERT INTO `role_menu` VALUES (2, 107);
INSERT INTO `role_menu` VALUES (2, 108);
INSERT INTO `role_menu` VALUES (2, 109);
INSERT INTO `role_menu` VALUES (2, 110);
INSERT INTO `role_menu` VALUES (2, 111);
INSERT INTO `role_menu` VALUES (2, 112);
INSERT INTO `role_menu` VALUES (2, 113);
INSERT INTO `role_menu` VALUES (2, 114);
INSERT INTO `role_menu` VALUES (2, 115);
INSERT INTO `role_menu` VALUES (2, 116);
INSERT INTO `role_menu` VALUES (2, 117);
INSERT INTO `role_menu` VALUES (2, 118);
INSERT INTO `role_menu` VALUES (2, 119);
INSERT INTO `role_menu` VALUES (2, 120);
INSERT INTO `role_menu` VALUES (2, 121);
INSERT INTO `role_menu` VALUES (2, 122);
INSERT INTO `role_menu` VALUES (2, 123);
INSERT INTO `role_menu` VALUES (2, 124);
INSERT INTO `role_menu` VALUES (2, 125);
INSERT INTO `role_menu` VALUES (2, 126);
INSERT INTO `role_menu` VALUES (2, 127);
INSERT INTO `role_menu` VALUES (2, 128);
INSERT INTO `role_menu` VALUES (2, 129);
INSERT INTO `role_menu` VALUES (3, 1);
INSERT INTO `role_menu` VALUES (3, 2);
INSERT INTO `role_menu` VALUES (3, 3);
INSERT INTO `role_menu` VALUES (3, 4);
INSERT INTO `role_menu` VALUES (3, 5);
INSERT INTO `role_menu` VALUES (3, 6);
INSERT INTO `role_menu` VALUES (3, 7);
INSERT INTO `role_menu` VALUES (3, 8);
INSERT INTO `role_menu` VALUES (3, 9);
INSERT INTO `role_menu` VALUES (3, 10);
INSERT INTO `role_menu` VALUES (3, 11);
INSERT INTO `role_menu` VALUES (3, 12);
INSERT INTO `role_menu` VALUES (3, 100);
INSERT INTO `role_menu` VALUES (3, 101);
INSERT INTO `role_menu` VALUES (3, 102);
INSERT INTO `role_menu` VALUES (3, 103);
INSERT INTO `role_menu` VALUES (3, 104);
INSERT INTO `role_menu` VALUES (3, 105);
INSERT INTO `role_menu` VALUES (3, 106);
INSERT INTO `role_menu` VALUES (3, 107);
INSERT INTO `role_menu` VALUES (3, 108);
INSERT INTO `role_menu` VALUES (3, 109);
INSERT INTO `role_menu` VALUES (3, 110);
INSERT INTO `role_menu` VALUES (3, 111);
INSERT INTO `role_menu` VALUES (3, 112);
INSERT INTO `role_menu` VALUES (3, 113);
INSERT INTO `role_menu` VALUES (3, 114);
INSERT INTO `role_menu` VALUES (3, 115);
INSERT INTO `role_menu` VALUES (3, 116);
INSERT INTO `role_menu` VALUES (3, 117);
INSERT INTO `role_menu` VALUES (3, 118);
INSERT INTO `role_menu` VALUES (3, 119);
INSERT INTO `role_menu` VALUES (3, 120);
INSERT INTO `role_menu` VALUES (3, 121);
INSERT INTO `role_menu` VALUES (3, 122);
INSERT INTO `role_menu` VALUES (3, 123);
INSERT INTO `role_menu` VALUES (3, 124);
INSERT INTO `role_menu` VALUES (3, 125);
INSERT INTO `role_menu` VALUES (3, 126);
INSERT INTO `role_menu` VALUES (3, 127);
INSERT INTO `role_menu` VALUES (3, 128);
INSERT INTO `role_menu` VALUES (3, 129);
INSERT INTO `role_menu` VALUES (4, 1);
INSERT INTO `role_menu` VALUES (4, 2);
INSERT INTO `role_menu` VALUES (4, 3);
INSERT INTO `role_menu` VALUES (4, 4);
INSERT INTO `role_menu` VALUES (4, 5);
INSERT INTO `role_menu` VALUES (4, 7);
INSERT INTO `role_menu` VALUES (4, 9);
INSERT INTO `role_menu` VALUES (4, 10);
INSERT INTO `role_menu` VALUES (4, 100);
INSERT INTO `role_menu` VALUES (4, 101);
INSERT INTO `role_menu` VALUES (4, 102);
INSERT INTO `role_menu` VALUES (4, 103);
INSERT INTO `role_menu` VALUES (4, 104);
INSERT INTO `role_menu` VALUES (4, 105);
INSERT INTO `role_menu` VALUES (4, 106);
INSERT INTO `role_menu` VALUES (4, 107);
INSERT INTO `role_menu` VALUES (4, 111);
INSERT INTO `role_menu` VALUES (4, 112);
INSERT INTO `role_menu` VALUES (4, 116);
INSERT INTO `role_menu` VALUES (4, 123);
INSERT INTO `role_menu` VALUES (4, 124);
INSERT INTO `role_menu` VALUES (4, 125);
INSERT INTO `role_menu` VALUES (4, 126);
INSERT INTO `role_menu` VALUES (4, 127);
INSERT INTO `role_menu` VALUES (5, 1);
INSERT INTO `role_menu` VALUES (5, 5);
INSERT INTO `role_menu` VALUES (5, 6);
INSERT INTO `role_menu` VALUES (5, 7);
INSERT INTO `role_menu` VALUES (5, 120);
INSERT INTO `role_menu` VALUES (5, 121);
INSERT INTO `role_menu` VALUES (5, 122);
INSERT INTO `role_menu` VALUES (5, 123);
INSERT INTO `role_menu` VALUES (5, 124);
INSERT INTO `role_menu` VALUES (5, 125);
INSERT INTO `role_menu` VALUES (6, 1);
INSERT INTO `role_menu` VALUES (6, 5);
INSERT INTO `role_menu` VALUES (6, 7);
INSERT INTO `role_menu` VALUES (6, 8);
INSERT INTO `role_menu` VALUES (6, 117);
INSERT INTO `role_menu` VALUES (6, 118);
INSERT INTO `role_menu` VALUES (6, 119);
INSERT INTO `role_menu` VALUES (6, 123);
INSERT INTO `role_menu` VALUES (6, 124);
INSERT INTO `role_menu` VALUES (6, 125);
INSERT INTO `role_menu` VALUES (7, 1);

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `salary_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '工资id',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `base_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '基础工资（元）',
  `week_time` int(0) NULL DEFAULT 0 COMMENT '周末加班(时长)',
  `festival_time` int(0) NULL DEFAULT 0 COMMENT '节日加班(时长)',
  `percentage` decimal(10, 2) NULL DEFAULT NULL COMMENT '提成（元）',
  `bonus` decimal(10, 2) NULL DEFAULT NULL COMMENT '奖金（元）',
  `absence_count` int(0) NULL DEFAULT 0 COMMENT '旷工(次数)',
  `late_count` int(0) NULL DEFAULT 0 COMMENT '迟到(次数)',
  `leave_count` int(0) NULL DEFAULT 0 COMMENT '请假(次数)',
  `leave` decimal(10, 2) NULL DEFAULT 30.00 COMMENT '请假扣薪标准',
  `late` decimal(10, 2) NULL DEFAULT 50.00 COMMENT '迟到扣薪标准',
  `absence` decimal(10, 2) NULL DEFAULT 70.00 COMMENT '旷工扣薪标准',
  `overtime` decimal(10, 2) NULL DEFAULT 60.00 COMMENT '加班薪资标准',
  `total_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '总计（元）',
  `checked` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否已审核(\'1\'=已审核，\'0\'=未审核(默认))',
  `check_result` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '审核结果（\'1\'=审核通过，\'0\'=审核未通过）',
  `fail_cause` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '审核失败原因',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`salary_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 255 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '薪资表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES (1, 1, 12943.00, 0, 1, 1330.00, 2819.00, 2, 1, 0, 30.00, 50.00, 30.00, 80.00, 3930.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-01 00:00:00', ' ');
INSERT INTO `salary` VALUES (2, 2, 2861.00, 1, 0, 885.00, 1018.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 4470.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (171, 3, 5506.00, 1, 1, 461.00, 2442.00, 0, 0, 0, 30.00, 30.00, 70.00, 60.00, 4530.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (172, 4, 3129.00, 2, 1, 738.00, 987.00, 2, 1, 0, 30.00, 50.00, 70.00, 60.00, 3860.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (173, 5, 4020.00, 2, 2, 719.00, 2810.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 6690.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (174, 6, 13033.00, 3, 2, 730.00, 2796.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 8300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (175, 7, 9586.00, 2, 1, 883.00, 2129.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 8100.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (176, 8, 11065.00, 3, 2, 952.00, 655.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 4480.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (177, 9, 2595.00, 0, 2, 364.00, 2694.00, 0, 1, 2, 30.00, 60.00, 30.00, 60.00, 6260.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (178, 10, 2626.00, 1, 0, 1355.00, 2817.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 4470.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (179, 11, 5666.00, 3, 2, 1231.00, 1974.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 4680.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (180, 12, 3463.00, 3, 0, 527.00, 2201.00, 2, 0, 1, 30.00, 50.00, 70.00, 60.00, 4890.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (181, 13, 2204.00, 3, 0, 1379.00, 1449.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 4920.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (182, 14, 5011.00, 1, 1, 319.00, 1404.00, 0, 0, 0, 30.00, 30.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (184, 1, 14258.00, 1, 0, 1067.00, 2079.00, 1, 0, 2, 30.00, 50.00, 70.00, 80.00, 3570.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (185, 2, 4895.00, 2, 1, 1243.00, 758.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 4650.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (186, 3, 6705.00, 3, 1, 1050.00, 630.00, 2, 0, 1, 30.00, 50.00, 70.00, 60.00, 3700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (187, 4, 14728.00, 0, 0, 1369.00, 1373.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 3600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (188, 5, 11237.00, 0, 1, 923.00, 1258.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 6300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (189, 6, 2015.00, 0, 1, 1371.00, 614.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (190, 7, 12160.00, 3, 1, 865.00, 2827.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 8000.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (191, 8, 13184.00, 1, 0, 1248.00, 2587.00, 2, 0, 2, 30.00, 50.00, 70.00, 60.00, 4400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (192, 9, 4739.00, 1, 2, 611.00, 2127.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 5600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (193, 10, 14495.00, 0, 2, 1474.00, 1801.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 3960.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (194, 11, 8565.00, 0, 1, 1088.00, 1348.00, 0, 2, 1, 30.00, 50.00, 70.00, 60.00, 4200.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (195, 12, 11973.00, 0, 2, 863.00, 2754.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 4700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (196, 13, 13364.00, 2, 0, 1258.00, 1344.00, 2, 1, 2, 30.00, 50.00, 70.00, 60.00, 4770.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (197, 14, 2544.00, 3, 2, 1361.00, 2374.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 7700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (198, 1, 6419.00, 1, 1, 387.00, 2296.00, 0, 0, 0, 30.00, 50.00, 70.00, 80.00, 3570.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (199, 2, 6192.00, 0, 0, 369.00, 2027.00, 0, 2, 1, 30.00, 50.00, 70.00, 60.00, 4650.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (200, 3, 12903.00, 2, 1, 1090.00, 1210.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 3700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (201, 4, 2735.00, 0, 0, 1165.00, 2003.00, 1, 1, 0, 30.00, 50.00, 70.00, 60.00, 3600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (202, 5, 14136.00, 1, 0, 725.00, 2317.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 6300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (203, 6, 3796.00, 1, 0, 1083.00, 948.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (204, 7, 11677.00, 0, 2, 1318.00, 1712.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 8000.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (205, 8, 6705.00, 2, 2, 477.00, 2696.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 4400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (206, 9, 6284.00, 3, 0, 814.00, 1233.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 5600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (207, 10, 7246.00, 2, 0, 1239.00, 941.00, 2, 0, 2, 30.00, 50.00, 70.00, 60.00, 3960.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (208, 11, 14756.00, 3, 0, 903.00, 984.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 4200.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (209, 12, 6866.00, 1, 1, 1018.00, 1521.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 4700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (210, 13, 14419.00, 1, 2, 1235.00, 2528.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 4770.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (211, 14, 8294.00, 3, 2, 627.00, 1717.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 7700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (212, 1, 5717.00, 1, 2, 529.00, 1701.00, 1, 0, 1, 30.00, 50.00, 70.00, 80.00, 3570.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (213, 2, 6654.00, 1, 2, 325.00, 777.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 4650.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (214, 3, 12656.00, 2, 2, 671.00, 663.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 3700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (215, 4, 9525.00, 3, 1, 637.00, 2132.00, 2, 0, 2, 30.00, 50.00, 70.00, 60.00, 3600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (216, 5, 6009.00, 1, 0, 1341.00, 2765.00, 0, 2, 1, 30.00, 50.00, 70.00, 60.00, 6300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (217, 6, 3423.00, 0, 0, 977.00, 1639.00, 1, 0, 1, 30.00, 50.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (218, 7, 6873.00, 2, 1, 387.00, 1144.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 8000.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (219, 8, 10040.00, 1, 2, 432.00, 1059.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 4400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (220, 9, 9774.00, 1, 2, 736.00, 2480.00, 1, 1, 0, 30.00, 50.00, 70.00, 60.00, 5600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (221, 10, 14489.00, 3, 1, 438.00, 1694.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 3960.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (222, 11, 9992.00, 1, 1, 721.00, 1523.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 4200.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (223, 12, 9171.00, 0, 0, 781.00, 1038.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 4700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (224, 13, 6468.00, 2, 0, 1105.00, 2303.00, 1, 0, 1, 30.00, 50.00, 70.00, 60.00, 4770.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (225, 14, 6332.00, 0, 0, 810.00, 1384.00, 1, 2, 2, 30.00, 50.00, 70.00, 60.00, 7700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (227, 1, 3065.00, 0, 0, 681.00, 2686.00, 1, 1, 0, 30.00, 50.00, 70.00, 80.00, 3570.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (228, 2, 13308.00, 2, 1, 1485.00, 986.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 4650.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '注意不要再迟到了');
INSERT INTO `salary` VALUES (229, 3, 8278.00, 1, 1, 432.00, 626.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 3700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (230, 4, 10422.00, 3, 0, 800.00, 2840.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 3600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (231, 5, 5043.00, 1, 2, 417.00, 1668.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 6300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (232, 6, 9466.00, 3, 1, 1178.00, 1215.00, 2, 0, 2, 30.00, 50.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (233, 7, 2203.00, 2, 1, 1033.00, 2281.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 8000.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (234, 8, 12391.00, 2, 1, 1299.00, 1569.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 4400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (235, 9, 11038.00, 2, 0, 717.00, 1556.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 5600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (236, 10, 6711.00, 1, 1, 449.00, 2829.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 3960.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (237, 11, 14738.00, 2, 1, 655.00, 2136.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 4200.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (238, 12, 13333.00, 3, 0, 422.00, 1943.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 4700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (239, 13, 14796.00, 3, 0, 1338.00, 1623.00, 2, 1, 2, 30.00, 50.00, 70.00, 60.00, 4770.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (240, 14, 6282.00, 3, 0, 1022.00, 2850.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 7700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (241, 1, 5371.00, 1, 1, 1379.00, 1322.00, 0, 0, 1, 30.00, 50.00, 70.00, 80.00, 3570.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (242, 2, 8106.00, 1, 0, 811.00, 2797.00, 1, 0, 1, 30.00, 50.00, 70.00, 60.00, 4650.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '注意不要再迟到了');
INSERT INTO `salary` VALUES (243, 3, 10291.00, 2, 1, 630.00, 2400.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 3700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (244, 4, 4764.00, 2, 0, 1118.00, 778.00, 1, 1, 2, 30.00, 50.00, 70.00, 60.00, 3600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (245, 5, 13901.00, 3, 1, 446.00, 2546.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 6300.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (246, 6, 14540.00, 3, 2, 706.00, 2328.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 7670.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (247, 7, 3439.00, 0, 2, 1356.00, 694.00, 1, 1, 0, 30.00, 50.00, 70.00, 60.00, 8000.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (248, 8, 13218.00, 0, 1, 1300.00, 2933.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 4400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (249, 9, 7970.00, 0, 0, 885.00, 783.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 5600.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (250, 10, 3008.00, 2, 0, 887.00, 626.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 3960.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (251, 11, 8959.00, 0, 1, 979.00, 1745.00, 1, 2, 2, 30.00, 50.00, 70.00, 60.00, 4200.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (252, 12, 5806.00, 0, 0, 1414.00, 643.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 4700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (253, 13, 5665.00, 2, 2, 396.00, 1072.00, 0, 1, 1, 30.00, 50.00, 70.00, 60.00, 4770.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (254, 14, 3857.00, 0, 1, 348.00, 1646.00, 0, 1, 2, 30.00, 50.00, 70.00, 60.00, 7700.00, '1', '1', '', '2021-08-25 08:44:46', '2021-08-27 00:32:16', '1', '2021-03-22 19:38:53', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录帐号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `salt` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户昵称',
  `real_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别（0=未知(默认)，1=女，2=男）',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄（0(默认)）',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `phone_number` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户状态(0=正常(默认)，1=停用)',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志(0=存在(默认)，2=删除)',
  `creater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 2, 'root', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', '至高无上', '张三', '1', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-25 14:48:33', NULL);
INSERT INTO `user` VALUES (2, 3, 'hr', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', '', '', '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-16 19:01:30', '人力资源工作人员');
INSERT INTO `user` VALUES (3, 4, 'user1', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', '至高无上', '张三', '0', 25, '1982989137@qq.com', '13788395907', '0', '2', '1', '2021-07-26 17:58:37', '1', '2021-08-24 17:53:44', '超级管理员');
INSERT INTO `user` VALUES (4, 4, 'user2', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:27:37', '人力资源工作人员');
INSERT INTO `user` VALUES (5, 4, 'user3', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:29:26', '超级管理员');
INSERT INTO `user` VALUES (6, 3, 'user4', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:28:10', '人力资源工作人员');
INSERT INTO `user` VALUES (7, 4, 'user5', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:29:36', '超级管理员');
INSERT INTO `user` VALUES (8, 7, 'user6', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:28:21', '人力资源工作人员');
INSERT INTO `user` VALUES (9, 7, 'user7', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:29:45', '超级管理员');
INSERT INTO `user` VALUES (10, 5, 'user8', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:30:30', '人力资源工作人员');
INSERT INTO `user` VALUES (11, 7, 'user9', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:29:58', '超级管理员');
INSERT INTO `user` VALUES (12, 3, 'user10', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:28:42', '人力资源工作人员');
INSERT INTO `user` VALUES (13, 4, 'user11', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:30:07', '超级管理员');
INSERT INTO `user` VALUES (14, 2, 'user12', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-27 17:29:12', '人力资源工作人员');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'PK，用户id',
  `role_id` int(0) NOT NULL COMMENT 'PK，角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色关联表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 5);
INSERT INTO `user_role` VALUES (4, 4);
INSERT INTO `user_role` VALUES (5, 7);
INSERT INTO `user_role` VALUES (6, 6);
INSERT INTO `user_role` VALUES (7, 7);
INSERT INTO `user_role` VALUES (8, 4);
INSERT INTO `user_role` VALUES (9, 7);
INSERT INTO `user_role` VALUES (10, 2);
INSERT INTO `user_role` VALUES (11, 7);
INSERT INTO `user_role` VALUES (12, 7);
INSERT INTO `user_role` VALUES (13, 7);
INSERT INTO `user_role` VALUES (14, 5);
INSERT INTO `user_role` VALUES (15, 5);
INSERT INTO `user_role` VALUES (16, 5);
INSERT INTO `user_role` VALUES (17, 5);
INSERT INTO `user_role` VALUES (18, 5);
INSERT INTO `user_role` VALUES (19, 7);
INSERT INTO `user_role` VALUES (21, 5);
INSERT INTO `user_role` VALUES (22, 5);
INSERT INTO `user_role` VALUES (23, 5);
INSERT INTO `user_role` VALUES (24, 5);
INSERT INTO `user_role` VALUES (25, 5);
INSERT INTO `user_role` VALUES (26, 5);
INSERT INTO `user_role` VALUES (27, 7);
INSERT INTO `user_role` VALUES (28, 7);
INSERT INTO `user_role` VALUES (32, 7);

SET FOREIGN_KEY_CHECKS = 1;
