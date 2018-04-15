
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_configuration
-- ----------------------------
DROP TABLE IF EXISTS `t_configuration`;
CREATE TABLE `t_configuration` (
  `id` varchar(32) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `key` varchar(200) DEFAULT NULL,
  `value` varchar(2000) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enabled_flag` char(1) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

