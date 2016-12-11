/*
 Navicat Premium Data Transfer

 Source Server         : 121.201.62.48
 Source Server Type    : MySQL
 Source Server Version : 50630
 Source Host           : 121.201.62.48
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50630
 File Encoding         : utf-8

 Date: 12/11/2016 10:27:00 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_test`
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `aa` datetime DEFAULT NULL,
  `bb` decimal(10,0) DEFAULT NULL,
  `cc` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_test_2`
-- ----------------------------
DROP TABLE IF EXISTS `t_test_2`;
CREATE TABLE `t_test_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `aa` datetime DEFAULT NULL,
  `bb` decimal(10,0) DEFAULT NULL,
  `cc` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
