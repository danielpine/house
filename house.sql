/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-04-20 22:28:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `houselist`
-- ----------------------------
DROP TABLE IF EXISTS `houselist`;
CREATE TABLE `houselist` (
  `houseid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `detail` varchar(2048) DEFAULT NULL,
  `userlist_id` int DEFAULT NULL,
  `userlist_name` varchar(64) DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`houseid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of houselist
-- ----------------------------
INSERT INTO `houselist` VALUES ('17', '深圳市盐田区沙头角856号4楼', '1300.00', '已出租', '1、不能使用灶火。\n2、基本家电齐全，拎包入住。', '31', '何小琳', '上海');
INSERT INTO `houselist` VALUES ('20', '深圳市盐田区大梅沙66号1楼', '900.00', '已出租', '无', '32', '王大锤', '上海');
INSERT INTO `houselist` VALUES ('21', '深圳市盐田区大梅沙66号3楼', '800.00', '已出租', '无', '29', '周晓二', '上海');
INSERT INTO `houselist` VALUES ('23', '深圳市龙华新区民塘路绿景公馆1699（南区）', '2000.00', '未出租', '只租女生，爱干净', null, null, '北京');
INSERT INTO `houselist` VALUES ('26', '深圳市盐田区大梅沙96号1楼', '900.00', '未出租', '家电齐全，拎包入住。', null, null, '北京');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `houseid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `paid`
-- ----------------------------
DROP TABLE IF EXISTS `paid`;
CREATE TABLE `paid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `paydate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `houseid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paid
-- ----------------------------
INSERT INTO `paid` VALUES ('30', '深圳市盐田区沙头角856号4楼', '1300.00', '2019-04-01', null, '何小琳', '31', '未缴', '17');
INSERT INTO `paid` VALUES ('31', '深圳市盐田区大梅沙66号1楼', '900.00', '2019-04-01', null, '王大锤', '32', '未缴', '20');
INSERT INTO `paid` VALUES ('32', '深圳市盐田区大梅沙66号3楼', '800.00', '2019-04-01', null, '周晓二', '29', '未缴', '21');

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` int DEFAULT NULL COMMENT '公告持续天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('6', '2019-04-01', '注意及时缴纳本月房租', '7');
INSERT INTO `schedule` VALUES ('8', '2019-04-15', '电梯停止使用一周，敬请见谅。', '7');
INSERT INTO `schedule` VALUES ('9', '2019-04-15', '本月还未缴纳月租的租客请注意及时缴纳月租。', '7');

-- ----------------------------
-- Table structure for `solve`
-- ----------------------------
DROP TABLE IF EXISTS `solve`;
CREATE TABLE `solve` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `houseid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of solve
-- ----------------------------
INSERT INTO `solve` VALUES ('11', '深圳市盐田区沙头角856号4楼', '2019-04-16', '房门门栓损坏。', '何小琳', '31', '未处理', '17');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '1为管理员，2为普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '88888888', '1');
INSERT INTO `user` VALUES ('19', '13192664967', '13192664967', '2');
INSERT INTO `user` VALUES ('21', '13192665859', '13192665859', '2');

-- ----------------------------
-- Table structure for `userlist`
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES ('27', '李老铁', '440582199712267459', '13199772826', '1');
INSERT INTO `userlist` VALUES ('29', '周晓二', '445215966585236985', '13192664967', '19');
INSERT INTO `userlist` VALUES ('31', '何小琳', '440582199512137569', '13192665859', '21');
