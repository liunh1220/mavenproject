
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `enabled_flag` char(1) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'aaa', '96e79218965eb72c92a549dd5a330112', 'y', '2017-04-19 17:21:31');
INSERT INTO `t_user` VALUES ('2', 'sss', '96e79218965eb72c92a549dd5a330112', 'Y', '2017-09-26 16:53:32');
INSERT INTO `t_user` VALUES ('3', 'sss', '96e79218965eb72c92a549dd5a330112', 'Y', '2017-09-26 19:00:24');
