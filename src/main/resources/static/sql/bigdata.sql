/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.9.61
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.104.9.61:3306
 Source Schema         : bigdata

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/11/2020 21:12:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for boke
-- ----------------------------
DROP TABLE IF EXISTS `boke`;
CREATE TABLE `boke`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uploadDate` datetime(0) NOT NULL COMMENT '上传时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `title` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `html` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'html内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bokePic
-- ----------------------------
DROP TABLE IF EXISTS `bokePic`;
CREATE TABLE `bokePic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `articleId` int(0) NULL DEFAULT NULL,
  `picPath` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fiction
-- ----------------------------
DROP TABLE IF EXISTS `fiction`;
CREATE TABLE `fiction`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `imgSrc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `href` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `updateTime` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for iqiyi
-- ----------------------------
DROP TABLE IF EXISTS `iqiyi`;
CREATE TABLE `iqiyi`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `href` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电视剧的链接',
  `imgSrc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电视剧图片的地址',
  `jiNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `channelId` int(0) NOT NULL,
  `dataType` int(0) NOT NULL,
  `aid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `json` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stars` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sucai
-- ----------------------------
DROP TABLE IF EXISTS `sucai`;
CREATE TABLE `sucai`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sucaiUrl` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tengxun
-- ----------------------------
DROP TABLE IF EXISTS `tengxun`;
CREATE TABLE `tengxun`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `href` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电视剧的链接',
  `imgSrc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电视剧图片的地址',
  `jiNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `channelId` int(0) NOT NULL COMMENT '1：电视剧，2：电影',
  `stars` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tengxun_manhua
-- ----------------------------
DROP TABLE IF EXISTS `tengxun_manhua`;
CREATE TABLE `tengxun_manhua`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漫画标题',
  `score` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评分',
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `popularity` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `collectionNumber` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `allChapter` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所有章节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
