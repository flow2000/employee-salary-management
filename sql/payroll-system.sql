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

 Date: 05/09/2021 18:10:15
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
INSERT INTO `dept` VALUES (23, '3123213', '123', '1234321312', '123189@qq.com', '0', '0', '1', '2021-09-04 23:23:29', '', NULL);
INSERT INTO `dept` VALUES (22, 'cas', '123', '1234321312', '123189@qq.com', '0', '0', '1', '2021-09-04 23:23:05', '', NULL);
INSERT INTO `dept` VALUES (5, '总裁办公室', '王五', '18952328733', '28316892@fox.com', '0', '0', '1', '2021-08-25 14:06:55', '', NULL);
INSERT INTO `dept` VALUES (6, 'test', 'teset', '13788395907', '1982989137@qq.com', '0', '0', '1', '2021-08-25 14:07:25', '1', '2021-08-31 10:54:34');
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
INSERT INTO `menu` VALUES (130, '合并部门', 'B', 6, 4, '#', '_self', 'system:dept:merge', '#', '0', 'root', '2021-09-04 23:17:21', '', NULL, '合并部门');

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
INSERT INTO `role` VALUES (1, 'root', '系统管理员', '0', '0', 'root', '2021-07-26 17:08:57', '1', '2021-08-29 12:11:24', '系统管理员，拥有对系统的所有权限');
INSERT INTO `role` VALUES (2, 'ceo', '董事长', '0', '0', 'root', '2021-07-26 17:10:30', '1', '2021-08-25 13:48:57', '董事长负责管理公司重大决策');
INSERT INTO `role` VALUES (3, 'general_manager', '总经理', '0', '0', 'root', '2021-07-26 17:11:33', '1', '2021-08-27 17:25:34', '总经理负责管理部门经理的事项');
INSERT INTO `role` VALUES (4, 'department_manager', '部门经理', '0', '0', 'root', '2021-07-26 17:12:37', '1', '2021-08-31 10:56:45', '部门经理负责管理本部门的事项');
INSERT INTO `role` VALUES (5, 'hr', '人力资源工作人员', '0', '0', 'root', '2021-07-26 17:14:43', '1', '2021-09-04 23:26:33', '人力资源工作人员负责考勤和人员信息的变动');
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
INSERT INTO `role_menu` VALUES (1, 130);
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
INSERT INTO `role_menu` VALUES (5, 2);
INSERT INTO `role_menu` VALUES (5, 4);
INSERT INTO `role_menu` VALUES (5, 5);
INSERT INTO `role_menu` VALUES (5, 7);
INSERT INTO `role_menu` VALUES (5, 102);
INSERT INTO `role_menu` VALUES (5, 114);
INSERT INTO `role_menu` VALUES (5, 115);
INSERT INTO `role_menu` VALUES (5, 116);
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
) ENGINE = InnoDB AUTO_INCREMENT = 270 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '薪资表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES (1, 1, 10483.00, 3, 0, 628.00, 1050.00, 2, 0, 2, 30.00, 50.00, 30.00, 80.00, 12521.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-01 00:00:00', ' ');
INSERT INTO `salary` VALUES (2, 2, 6843.00, 1, 1, 308.00, 1431.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 8642.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (171, 3, 10548.00, 1, 0, 645.00, 1930.00, 2, 1, 0, 30.00, 30.00, 70.00, 60.00, 13073.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (172, 4, 8742.00, 3, 0, 1209.00, 1974.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 12255.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (173, 5, 12909.00, 2, 1, 1163.00, 728.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 15220.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (174, 6, 10354.00, 1, 2, 1048.00, 1642.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 13524.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (175, 7, 9003.00, 2, 2, 1072.00, 913.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 11588.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (176, 8, 5656.00, 3, 0, 480.00, 1639.00, 2, 2, 2, 30.00, 50.00, 70.00, 60.00, 7835.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (177, 9, 11520.00, 2, 1, 1359.00, 2829.00, 1, 1, 0, 30.00, 60.00, 30.00, 60.00, 16038.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (178, 10, 2550.00, 1, 1, 537.00, 2430.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 5767.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (179, 11, 2806.00, 1, 0, 750.00, 2895.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 6521.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (180, 12, 8062.00, 2, 0, 777.00, 1701.00, 2, 2, 1, 30.00, 50.00, 70.00, 60.00, 10510.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (181, 13, 11969.00, 0, 2, 1023.00, 2615.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 15727.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (182, 14, 9002.00, 2, 0, 346.00, 1086.00, 2, 0, 0, 30.00, 30.00, 70.00, 60.00, 10534.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-08-25 14:45:09', '');
INSERT INTO `salary` VALUES (184, 1, 8941.00, 1, 0, 667.00, 677.00, 0, 0, 2, 30.00, 50.00, 70.00, 80.00, 10385.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (185, 2, 11194.00, 2, 2, 652.00, 1850.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 14266.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (186, 3, 10776.00, 1, 2, 302.00, 878.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 12386.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (187, 4, 9219.00, 1, 0, 377.00, 2811.00, 0, 2, 2, 30.00, 50.00, 70.00, 60.00, 12367.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (188, 5, 4020.00, 0, 1, 840.00, 2357.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 7327.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (189, 6, 10243.00, 2, 0, 376.00, 2291.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 13090.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (190, 7, 5470.00, 0, 0, 1025.00, 872.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 7197.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (191, 8, 7870.00, 0, 1, 1227.00, 1008.00, 1, 1, 0, 30.00, 50.00, 70.00, 60.00, 10165.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (192, 9, 2001.00, 2, 0, 1310.00, 2912.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 6463.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (193, 10, 6512.00, 0, 0, 426.00, 950.00, 2, 1, 0, 30.00, 50.00, 70.00, 60.00, 7698.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (194, 11, 13874.00, 3, 2, 1259.00, 2074.00, 2, 2, 2, 30.00, 50.00, 70.00, 60.00, 17627.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (195, 12, 12721.00, 2, 0, 1003.00, 2884.00, 0, 1, 2, 30.00, 50.00, 70.00, 60.00, 16738.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (196, 13, 5256.00, 0, 1, 990.00, 609.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 6795.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (197, 14, 14215.00, 1, 0, 849.00, 1199.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 16243.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-07-25 14:45:09', '');
INSERT INTO `salary` VALUES (198, 1, 2756.00, 0, 0, 1378.00, 747.00, 0, 2, 1, 30.00, 50.00, 70.00, 80.00, 4751.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (199, 2, 12004.00, 2, 1, 1338.00, 1771.00, 0, 2, 1, 30.00, 50.00, 70.00, 60.00, 15403.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (200, 3, 2789.00, 3, 0, 624.00, 2058.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 5661.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (201, 4, 12114.00, 2, 2, 1034.00, 1472.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 15020.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (202, 5, 10829.00, 3, 2, 1464.00, 2452.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 15405.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (203, 6, 12456.00, 1, 2, 849.00, 1049.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 14784.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (204, 7, 2820.00, 3, 0, 749.00, 1470.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 5299.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (205, 8, 7974.00, 1, 0, 401.00, 2551.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 10916.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (206, 9, 8019.00, 1, 0, 827.00, 2647.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 11413.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (207, 10, 9226.00, 0, 1, 341.00, 2146.00, 0, 2, 2, 30.00, 50.00, 70.00, 60.00, 11733.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (208, 11, 5326.00, 3, 0, 1106.00, 1061.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 7713.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (209, 12, 13572.00, 0, 2, 1194.00, 2863.00, 1, 0, 1, 30.00, 50.00, 70.00, 60.00, 17889.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (210, 13, 14717.00, 3, 2, 1166.00, 2208.00, 2, 1, 0, 30.00, 50.00, 70.00, 60.00, 18621.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (211, 14, 14030.00, 1, 0, 1105.00, 2903.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 17918.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-06-25 14:45:09', '');
INSERT INTO `salary` VALUES (212, 1, 6645.00, 2, 0, 449.00, 924.00, 0, 1, 0, 30.00, 50.00, 70.00, 80.00, 8288.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (213, 2, 6877.00, 1, 0, 928.00, 764.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 8689.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-24 01:03:56', '注意不要再迟到了');
INSERT INTO `salary` VALUES (214, 3, 3349.00, 3, 0, 614.00, 2886.00, 2, 1, 0, 30.00, 50.00, 70.00, 60.00, 7019.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (215, 4, 12393.00, 0, 0, 1108.00, 1808.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 15259.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (216, 5, 13758.00, 0, 0, 704.00, 2979.00, 0, 2, 1, 30.00, 50.00, 70.00, 60.00, 17311.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (217, 6, 4605.00, 1, 1, 745.00, 2469.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 8089.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (218, 7, 6326.00, 1, 0, 364.00, 963.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 7773.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (219, 8, 14897.00, 3, 0, 479.00, 2430.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 18036.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (220, 9, 2849.00, 0, 2, 731.00, 638.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 4578.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (221, 10, 5574.00, 3, 2, 1141.00, 2304.00, 2, 1, 1, 30.00, 50.00, 70.00, 60.00, 9519.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (222, 11, 3721.00, 2, 0, 1194.00, 1342.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 6397.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (223, 12, 11120.00, 3, 1, 1148.00, 2738.00, 1, 1, 0, 30.00, 50.00, 70.00, 60.00, 15426.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (224, 13, 13778.00, 1, 0, 870.00, 1216.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 15984.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (225, 14, 8336.00, 2, 2, 1182.00, 1918.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 11986.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-05-25 14:45:09', '');
INSERT INTO `salary` VALUES (227, 1, 4544.00, 3, 2, 1472.00, 2665.00, 0, 0, 0, 30.00, 50.00, 70.00, 80.00, 9641.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (228, 2, 9268.00, 2, 0, 839.00, 1049.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 11366.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '注意不要再迟到了');
INSERT INTO `salary` VALUES (229, 3, 9409.00, 3, 1, 961.00, 2979.00, 2, 2, 1, 30.00, 50.00, 70.00, 60.00, 13619.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (230, 4, 4252.00, 0, 2, 372.00, 1253.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 6097.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (231, 5, 4931.00, 3, 0, 1231.00, 1895.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 8387.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (232, 6, 4411.00, 3, 1, 701.00, 1970.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 7622.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (233, 7, 4626.00, 3, 1, 765.00, 2458.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 8319.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (234, 8, 4576.00, 3, 0, 1381.00, 2126.00, 1, 1, 1, 30.00, 50.00, 70.00, 60.00, 8293.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (235, 9, 4109.00, 1, 1, 867.00, 2770.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 7946.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (236, 10, 8766.00, 2, 2, 1405.00, 1412.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 12113.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (237, 11, 14717.00, 3, 2, 622.00, 1035.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 17034.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (238, 12, 12666.00, 2, 1, 1454.00, 2018.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 16558.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (239, 13, 9260.00, 0, 0, 738.00, 895.00, 0, 1, 1, 30.00, 50.00, 70.00, 60.00, 10813.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (240, 14, 9383.00, 2, 0, 537.00, 2300.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 12430.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-04-22 19:38:53', '');
INSERT INTO `salary` VALUES (241, 1, 4739.00, 1, 1, 1031.00, 908.00, 2, 0, 2, 30.00, 50.00, 70.00, 80.00, 6878.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:50', '1', '2021-03-22 19:38:53', ' ');
INSERT INTO `salary` VALUES (242, 2, 9926.00, 3, 2, 387.00, 700.00, 2, 1, 2, 30.00, 50.00, 70.00, 60.00, 11483.00, '1', '1', ' ', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '注意不要再迟到了');
INSERT INTO `salary` VALUES (243, 3, 2073.00, 0, 0, 1155.00, 2189.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 5177.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (244, 4, 7766.00, 0, 0, 474.00, 2015.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 10255.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (245, 5, 7847.00, 2, 0, 645.00, 1983.00, 2, 1, 0, 30.00, 50.00, 70.00, 60.00, 10525.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (246, 6, 11061.00, 0, 0, 451.00, 1072.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 12534.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (247, 7, 8109.00, 3, 0, 303.00, 2494.00, 1, 0, 2, 30.00, 50.00, 70.00, 60.00, 11136.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (248, 8, 8119.00, 3, 0, 706.00, 2075.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 11190.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (249, 9, 14027.00, 0, 0, 1008.00, 2465.00, 0, 2, 0, 30.00, 50.00, 70.00, 60.00, 17400.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (250, 10, 5088.00, 2, 0, 1065.00, 1042.00, 2, 2, 2, 30.00, 50.00, 70.00, 60.00, 7135.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (251, 11, 11523.00, 0, 0, 659.00, 1297.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 13339.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (252, 12, 14785.00, 0, 1, 475.00, 2276.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 17656.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (253, 13, 11008.00, 1, 0, 767.00, 948.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 12773.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (254, 14, 14284.00, 3, 2, 1358.00, 1121.00, 1, 0, 0, 30.00, 50.00, 70.00, 60.00, 17413.00, '1', '1', '', '2021-08-25 08:44:46', '2021-09-03 16:26:51', '1', '2021-03-22 19:38:53', '');
INSERT INTO `salary` VALUES (255, 1, 6215.00, 3, 0, 1125.00, 1673.00, 0, 1, 0, 30.00, 50.00, 70.00, 60.00, 9323.00, '0', '', '', NULL, '2021-09-03 16:26:51', NULL, '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (256, 2, 12630.00, 0, 0, 978.00, 974.00, 0, 1, 1, 30.00, 50.00, 70.00, 60.00, 14502.00, '1', '1', '', '2021-09-03 16:33:17', '2021-09-03 16:33:17', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (257, 3, 13364.00, 1, 0, 1475.00, 2491.00, 2, 0, 1, 30.00, 50.00, 70.00, 60.00, 17280.00, '1', '1', '', '2021-09-03 16:35:04', '2021-09-03 16:35:04', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (258, 4, 13778.00, 1, 2, 721.00, 2858.00, 1, 2, 1, 30.00, 50.00, 70.00, 60.00, 17637.00, '1', '1', '', '2021-09-03 16:33:11', '2021-09-03 16:33:11', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (259, 5, 6856.00, 2, 0, 645.00, 1064.00, 1, 2, 0, 30.00, 50.00, 70.00, 60.00, 8635.00, '1', '1', '', '2021-09-03 16:33:14', '2021-09-03 16:33:14', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (260, 6, 10083.00, 0, 0, 618.00, 2216.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 12917.00, '1', '1', '', '2021-09-03 16:33:08', '2021-09-03 16:33:08', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (261, 7, 4102.00, 3, 1, 1285.00, 2776.00, 0, 1, 2, 30.00, 50.00, 70.00, 60.00, 8593.00, '1', '1', '', '2021-09-03 16:33:05', '2021-09-03 16:33:05', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (262, 8, 3443.00, 3, 0, 909.00, 1686.00, 0, 0, 1, 30.00, 50.00, 70.00, 60.00, 6368.00, '1', '1', '', '2021-09-03 16:33:02', '2021-09-03 16:33:02', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (263, 9, 5833.00, 2, 1, 329.00, 794.00, 2, 0, 0, 30.00, 50.00, 70.00, 60.00, 7236.00, '1', '0', '考勤扣薪计算有误', '2021-09-03 16:36:44', '2021-09-03 16:36:44', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (264, 10, 13792.00, 3, 0, 1417.00, 923.00, 0, 1, 1, 30.00, 50.00, 70.00, 60.00, 16412.00, '0', '', '', NULL, '2021-09-03 16:26:51', NULL, '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (265, 11, 8997.00, 1, 0, 1023.00, 2105.00, 0, 0, 0, 30.00, 50.00, 70.00, 60.00, 12245.00, '1', '1', '', '2021-09-03 16:35:13', '2021-09-03 16:35:13', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (266, 12, 12609.00, 1, 2, 1355.00, 715.00, 1, 0, 1, 30.00, 50.00, 70.00, 60.00, 15059.00, '0', '', '', NULL, '2021-09-03 16:26:51', NULL, '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (267, 13, 7951.00, 0, 1, 573.00, 2733.00, 2, 2, 0, 30.00, 50.00, 70.00, 60.00, 11197.00, '1', '0', '出勤没有加完', '2021-09-03 16:35:48', '2021-09-03 16:35:48', '1', '2021-09-03 16:23:12', '');
INSERT INTO `salary` VALUES (268, 14, 2894.00, 0, 0, 877.00, 2222.00, 0, 0, 2, 30.00, 50.00, 70.00, 60.00, 5933.00, '1', '1', '', '2021-09-03 16:32:55', '2021-09-03 16:32:55', '1', '2021-09-03 16:23:12', '');

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
INSERT INTO `user` VALUES (1, NULL, 'root', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', '至高无上', '张三', '0', 40, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', 'root', '2021-09-04 23:07:41', NULL);
INSERT INTO `user` VALUES (2, 2, 'hr', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '2', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-09-03 16:18:13', '人力资源工作人员');
INSERT INTO `user` VALUES (3, 4, 'user1', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', '至高无上', '张三', '0', 25, '1982989137@qq.com', '13788395907', '0', '2', '1', '2021-07-26 17:58:37', '1', '2021-08-24 17:53:44', '超级管理员');
INSERT INTO `user` VALUES (4, 4, 'user2', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-31 10:55:07', '人力资源工作人员');
INSERT INTO `user` VALUES (5, 4, 'user3', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-26 17:58:37', '1', '2021-08-27 17:29:26', '超级管理员');
INSERT INTO `user` VALUES (6, 3, 'user4', 'b5f4eb9c0513b51d2c4fe9ac81232dec', 'MAjQ0H', NULL, NULL, '1', 31, '1982989137@qq.com', '13788395907', '0', '0', '1', '2021-07-29 09:27:59', '1', '2021-08-29 14:18:19', '人力资源工作人员');
INSERT INTO `user` VALUES (7, 4, 'user5', '271b8c02f09e42b0b1eddcf444b562be', 'JKQmhc', NULL, NULL, '0', 25, '1982989137@qq.com', '13788395907', '0', '2', '1', '2021-07-26 17:58:37', '1', '2021-09-03 16:18:36', '超级管理员');
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
INSERT INTO `user_role` VALUES (2, 4);
INSERT INTO `user_role` VALUES (4, 4);
INSERT INTO `user_role` VALUES (5, 7);
INSERT INTO `user_role` VALUES (6, 6);
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
