/*
Navicat MySQL Data Transfer

Source Server         : qiyuesuo
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-06 16:02:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uuid` char(48) DEFAULT NULL,
  `size` int(48) NOT NULL,
  `create_date` char(48) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
