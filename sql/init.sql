/*
 Navicat Premium Data Transfer

 Source Server         : crm
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : rm-2zep01apl77idn5c9io.mysql.rds.aliyuncs.com:3306
 Source Schema         : excel2db

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 06/12/2024 16:41:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_source
-- ----------------------------
DROP TABLE IF EXISTS `data_source`;
CREATE TABLE `data_source`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ds_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源名称',
  `db_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `host` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源主机',
  `port` int NULL DEFAULT NULL COMMENT '端口号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `database` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库',
  `link_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `file_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'excel名称',
  `sheet_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sheet名称',
  `header_num` int NULL DEFAULT NULL COMMENT '表头行数',
  `start_row_num` int NULL DEFAULT NULL COMMENT '开始行数',
  `total_row_num` int NULL DEFAULT NULL COMMENT '结束行数',
  `remove_end_num` int NULL DEFAULT NULL COMMENT '剔除最后行数',
  `store_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `ds_id` int NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_transform_config
-- ----------------------------
DROP TABLE IF EXISTS `task_transform_config`;
CREATE TABLE `task_transform_config`  (
  `transform_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NULL DEFAULT NULL COMMENT '任务类型',
  `source_column_num` int NULL DEFAULT NULL COMMENT '源端字段序号',
  `source_column` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '源端字段',
  `target_column` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标字段',
  `transform_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转换类型',
  `config_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转换数据',
  PRIMARY KEY (`transform_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
