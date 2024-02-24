SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`
(
    `clazz_name` varchar(50)         NOT NULL,
    `season`     tinyint(3) UNSIGNED NULL DEFAULT NULL,
    `period`     tinyint(3) UNSIGNED NULL DEFAULT NULL,
    `is_enable`  bit(1)              NULL DEFAULT NULL,
    `created_at` datetime(0)         NULL DEFAULT NULL,
    PRIMARY KEY (`clazz_name`)
);

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz`
VALUES ('计算机科学与技术 2020', 12, 8, b'1', '2021-01-23 15:14:07');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`
(
    `img_key`       char(150)     NOT NULL,
    `img_size`      decimal(9, 2) NULL DEFAULT NULL,
    `img_extension` char(5)       NULL DEFAULT NULL,
    PRIMARY KEY (`img_key`)
);

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for param
-- ----------------------------
DROP TABLE IF EXISTS `param`;
CREATE TABLE `param`
(
    `notice` text NULL
);

-- ----------------------------
-- Records of param
-- ----------------------------
INSERT INTO `param`
VALUES (NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`     char(12)    NOT NULL,
    `username`   varchar(12) NULL DEFAULT NULL,
    `pwd`        char(16)    NULL DEFAULT NULL,
    `role`       varchar(6)  NULL DEFAULT NULL,
    `clazz_name` varchar(50) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);

-- ----------------------------
-- Records of user
-- ----------------------------
-- START感谢使用黑鸣SQL数据生成工具
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('1', '卢花蕾', '1', 'admin', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202007028678', '席蝶', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202006089584', '酆土', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202016943761', '和卉怀', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202095958876', '孟北', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202015661836', '晋有', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202076145330', '郈它啥', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202085805763', '庄佯', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202023734833', '空振乡', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202092194293', '有拆', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202022317672', '太叔由', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202059430550', '王潍', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202009348687', '庞伞疥', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202089378993', '归代', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202096860716', '蒯娘', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202025395430', '喻氧墅', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202075385492', '於辈', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202018082592', '左臼', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202022991240', '邓竹', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202033149278', '水啥', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202055001467', '缪童', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202087169010', '潘易', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202028697907', '方噶', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202023595190', '夏侯曼翠', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202030110608', '申霜', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202036812921', '邱渊', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202089062133', '贝靴', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202092859808', '郗袖统', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202049600474', '安责监', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202032893136', '双育', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202028808588', '卫薪', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202073898616', '公羊腮', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202021240297', '韦盈', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202085867847', '丁案', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202049486854', '孟歧', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202088477226', '弘踊', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202022735433', '胡谭', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202023527840', '晋抑', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202058640341', '郜波', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202011443067', '毛拇赋', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202072539735', '范捡', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202000309082', '管萍冰', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202003542231', '时座', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202052703799', '廖俄', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202057347379', '葛曙', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202055028292', '章征', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202092168091', '鲍矫江', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202035480259', '虞界', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202021494452', '壤驷肖', '1', 'user', '计算机科学与技术 2020');
INSERT INTO `user` (`user_id`, `username`, `pwd`, `role`, `clazz_name`)
VALUES ('202097596172', '萧傲珊', '1', 'user', '计算机科学与技术 2020');
-- END感谢使用黑鸣SQL数据生成工具

-- ----------------------------
-- Table structure for upload
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload`
(
    `user_id`      char(12)    NOT NULL,
    `img_key`     char(150)   NOT NULL,
    `upload_time` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`, `img_key`)
);

-- ----------------------------
-- Records of upload
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 1;
