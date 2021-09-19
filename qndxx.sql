/*
 Navicat Premium Data Transfer

 Source Server         : aliyun_mysql8
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-bp1k55jgi0iuet22e2o.mysql.rds.aliyuncs.com:3306
 Source Schema         : qndxx

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 12/04/2021 21:39:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`
(
    `clazz_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `season`     tinyint(3) UNSIGNED                                          NULL DEFAULT NULL,
    `period`     tinyint(3) UNSIGNED                                          NULL DEFAULT NULL,
    `is_enable`  bit(1)                                                       NULL DEFAULT NULL,
    `created_at` datetime(0)                                                  NULL DEFAULT NULL,
    PRIMARY KEY (`clazz_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz`
VALUES ('计算机科学与技术18-1', 12, 8, b'1', '2021-01-23 15:14:07');
INSERT INTO `clazz`
VALUES ('软工升本20-2', 14, 3, b'1', '2021-01-10 02:29:55');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`
(
    `img_key`       char(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `img_size`      decimal(9, 2)                                              NULL DEFAULT NULL,
    `img_extension` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NULL DEFAULT NULL,
    PRIMARY KEY (`img_key`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for param
-- ----------------------------
DROP TABLE IF EXISTS `param`;
CREATE TABLE `param`
(
    `notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of param
-- ----------------------------
INSERT INTO `param`
VALUES (NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `stu_id`     char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL,
    `stu_name`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `pwd`        char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NULL DEFAULT NULL,
    `role`       varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL,
    `clazz_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student`
VALUES ('1', '唐三', '123', 'normal', '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('12', '小舞', 'null', 'null', '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('122', '戴沐白', NULL, NULL, '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('1221', '朱竹青', NULL, NULL, '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('12221', '宁荣荣', NULL, NULL, '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('12222', '奥斯卡', NULL, NULL, '计算机科学与技术18-1');
INSERT INTO `student`
VALUES ('13', '马红骏', NULL, NULL, '计算机科学与技术18-1');

-- ----------------------------
-- Table structure for upload
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload`
(
    `stu_id`      char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `img_key`     char(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `upload_time` datetime(0)                                                NULL DEFAULT NULL,
    PRIMARY KEY (`stu_id`, `img_key`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 1;
