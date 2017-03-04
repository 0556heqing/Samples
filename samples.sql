/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : samples

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-04 15:53:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `classDirector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fu9khrtclhj8x5rr9r3fpcbd2` (`classDirector_id`),
  CONSTRAINT `FK_fu9khrtclhj8x5rr9r3fpcbd2` FOREIGN KEY (`classDirector_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `superviseclass_Id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2lrxybqn1tke3riiy8fj594qp` (`superviseclass_Id`),
  CONSTRAINT `FK_2lrxybqn1tke3riiy8fj594qp` FOREIGN KEY (`superviseclass_Id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `_id` bigint(20) NOT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_headTeacherId` bigint(20) DEFAULT NULL,
  `_classDirectorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1488518040817', 'c2', null, '1488518042517');
INSERT INTO `t_class` VALUES ('1488518041111', 'c4', '1488518036767', '1488518036767');
INSERT INTO `t_class` VALUES ('1488518042151', 'c1', '1488518042517', '1488518042517');

-- ----------------------------
-- Table structure for `t_myjobdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_myjobdetail`;
CREATE TABLE `t_myjobdetail` (
  `_id` bigint(20) NOT NULL,
  `_className` varchar(255) DEFAULT NULL,
  `_methodsName` varchar(255) DEFAULT NULL,
  `_content` varchar(1000) DEFAULT NULL,
  `_describe` varchar(255) DEFAULT NULL,
  `_createUser` varchar(255) DEFAULT NULL,
  `_createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_myjobdetail
-- ----------------------------
INSERT INTO `t_myjobdetail` VALUES ('1488526606251', 'com.heqing.samplesFramework.quartz.task.Task', 'test', '{name:\'heqing\',age:27}', 'this is test', 'he qing', '2017-03-03 00:00:00');

-- ----------------------------
-- Table structure for `t_myscheduler`
-- ----------------------------
DROP TABLE IF EXISTS `t_myscheduler`;
CREATE TABLE `t_myscheduler` (
  `_id` bigint(20) NOT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_jobDetailId` bigint(20) DEFAULT NULL,
  `_triggerId` bigint(20) DEFAULT NULL,
  `_onOrOff` tinyint(10) DEFAULT NULL,
  `_describe` varchar(255) DEFAULT NULL,
  `_createUser` varchar(255) DEFAULT NULL,
  `_createTime` datetime DEFAULT NULL,
  `_group` varchar(255) DEFAULT NULL,
  `_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_myscheduler
-- ----------------------------
INSERT INTO `t_myscheduler` VALUES ('1488526606819', 'name', '1488526606251', '1488526602369', '0', null, null, '2017-03-03 00:00:00', 'group', '正常状态');
INSERT INTO `t_myscheduler` VALUES ('1488537633405', 'name1', '1488537628772', '1488537628517', '1', null, null, '2017-03-03 18:40:26', 'group1', 'NORMAL');

-- ----------------------------
-- Table structure for `t_mytrigger`
-- ----------------------------
DROP TABLE IF EXISTS `t_mytrigger`;
CREATE TABLE `t_mytrigger` (
  `_id` bigint(20) NOT NULL,
  `_rule` varchar(255) DEFAULT NULL,
  `_describe` varchar(255) DEFAULT NULL,
  `_createUser` varchar(255) DEFAULT NULL,
  `_createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mytrigger
-- ----------------------------
INSERT INTO `t_mytrigger` VALUES ('1488526602369', '0/5 * *  * * ? ', null, 'he qing', '2017-03-03 00:00:00');
INSERT INTO `t_mytrigger` VALUES ('1488537628517', '0/5 * *  * * ? ', null, 'heqing', '2017-03-03 18:40:26');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `_id` bigint(20) NOT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_birthDay` date DEFAULT NULL,
  `_superviseClassId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1488518036767', 't2', '1992-01-01', '1488518041111');
INSERT INTO `t_teacher` VALUES ('1488518042517', 't1', '1991-01-01', '1488518042151');

-- ----------------------------
-- Table structure for `t_teacher_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_class`;
CREATE TABLE `t_teacher_class` (
  `_classId` bigint(20) NOT NULL,
  `_teacherId` bigint(20) NOT NULL,
  PRIMARY KEY (`_classId`,`_teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher_class
-- ----------------------------
INSERT INTO `t_teacher_class` VALUES ('1488518040817', '1488518042517');
INSERT INTO `t_teacher_class` VALUES ('1488518041111', '1488518036767');
INSERT INTO `t_teacher_class` VALUES ('1488518042151', '1488518042517');
