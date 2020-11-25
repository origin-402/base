/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : train

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/08/2019 17:28:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `des` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72861 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES (72853, '用户管理页面', '系统管理员', '1', '2019-08-28 08:47:50', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72854, '用户列表', '系统管理员', '1', '2019-08-28 08:47:50', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72855, '用户管理页面', '系统管理员', '1', '2019-08-28 08:48:12', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72856, '用户列表', '系统管理员', '1', '2019-08-28 08:48:12', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72857, '用户管理页面', '系统管理员', '1', '2019-08-28 08:49:04', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72858, '用户列表', '系统管理员', '1', '2019-08-28 08:49:04', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72859, '用户管理页面', '系统管理员', '1', '2019-08-28 08:49:47', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72860, '用户列表', '系统管理员', '1', '2019-08-28 08:49:47', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72861, '用户管理页面', '系统管理员', '1', '2019-08-30 17:20:29', '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `system_log` VALUES (72862, '用户列表', '系统管理员', '1', '2019-08-30 17:20:30', '0:0:0:0:0:0:0:1', NULL);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:菜单权限  1:数据权限',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(6) NULL DEFAULT 0,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单（权限）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('0', '', '用户角色管理', '/system_role', '0', NULL, 0, NULL);
INSERT INTO `system_menu` VALUES ('01', '0', '角色管理', '/system_role/role_manage', '0', NULL, 1, NULL);
INSERT INTO `system_menu` VALUES ('011', '01', '权限树', '/system_role/findAllMenuForZtree', '1', NULL, 1, NULL);
INSERT INTO `system_menu` VALUES ('012', '01', '添加', '/system_role/saveRole', '1', NULL, 2, NULL);
INSERT INTO `system_menu` VALUES ('013', '01', '修改', '/system_role/updateRole', '1', NULL, 2, NULL);
INSERT INTO `system_menu` VALUES ('014', '01', '删除', '/system_role/delete', '1', NULL, 1, NULL);
INSERT INTO `system_menu` VALUES ('015', '01', '详情', '/system_role/findRoleById', '1', NULL, 2, NULL);
INSERT INTO `system_menu` VALUES ('02', '0', '系统用户管理', '/system_user/user_manage', '0', NULL, 3, NULL);
INSERT INTO `system_menu` VALUES ('021', '02', '列表', '/system_user/listUser', '1', NULL, 1, NULL);
INSERT INTO `system_menu` VALUES ('022', '02', '添加', '/system_user/saveUser', '1', NULL, 1, NULL);
INSERT INTO `system_menu` VALUES ('024', '02', '详情', '/system_user/findUserById', '1', NULL, 2, '');
INSERT INTO `system_menu` VALUES ('025', '02', '修改', '/system_user/updateUser', '1', NULL, 3, NULL);
INSERT INTO `system_menu` VALUES ('026', '02', '删除', '/system_user/deleteUser', '1', NULL, 4, NULL);
INSERT INTO `system_menu` VALUES ('027', '02', '重置密码', '/system_user/updatePassword', '1', NULL, 5, NULL);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('5c04cdbabc5949aba59cee7eb159d319', '超级管理员', NULL, '2017-02-25 17:09:06', '1');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80985 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES (80893, '5c04cdbabc5949aba59cee7eb159d319', '0');
INSERT INTO `system_role_menu` VALUES (80894, '5c04cdbabc5949aba59cee7eb159d319', '01');
INSERT INTO `system_role_menu` VALUES (80895, '5c04cdbabc5949aba59cee7eb159d319', '011');
INSERT INTO `system_role_menu` VALUES (80896, '5c04cdbabc5949aba59cee7eb159d319', '012');
INSERT INTO `system_role_menu` VALUES (80897, '5c04cdbabc5949aba59cee7eb159d319', '013');
INSERT INTO `system_role_menu` VALUES (80898, '5c04cdbabc5949aba59cee7eb159d319', '014');
INSERT INTO `system_role_menu` VALUES (80899, '5c04cdbabc5949aba59cee7eb159d319', '015');
INSERT INTO `system_role_menu` VALUES (80900, '5c04cdbabc5949aba59cee7eb159d319', '02');
INSERT INTO `system_role_menu` VALUES (80901, '5c04cdbabc5949aba59cee7eb159d319', '021');
INSERT INTO `system_role_menu` VALUES (80902, '5c04cdbabc5949aba59cee7eb159d319', '022');
INSERT INTO `system_role_menu` VALUES (80903, '5c04cdbabc5949aba59cee7eb159d319', '024');
INSERT INTO `system_role_menu` VALUES (80904, '5c04cdbabc5949aba59cee7eb159d319', '025');
INSERT INTO `system_role_menu` VALUES (80905, '5c04cdbabc5949aba59cee7eb159d319', '026');
INSERT INTO `system_role_menu` VALUES (80906, '5c04cdbabc5949aba59cee7eb159d319', '027');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别0：男，1女',
  `resign` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否启用：0启用 1禁用',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `delete_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  `delete_user` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除人，内容为x_id,    x:0表示平台用户，1表示社区或物业用户，2表示app用户',
  `delete_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '系统管理员', '15205312222', NULL, NULL, NULL, '2019-02-12 17:05:40', '0', '5c04cdbabc5949aba59cee7eb159d319', '0', NULL, '2019-07-26 09:54:42');

SET FOREIGN_KEY_CHECKS = 1;
