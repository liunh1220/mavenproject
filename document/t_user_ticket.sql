
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_user_ticket`;
CREATE TABLE `t_user_ticket` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_id` varchar(32) DEFAULT NULL COMMENT 't_user.id',
  `ticket_id` varchar(32) DEFAULT NULL COMMENT 't_ticket.id',
  `ticket_num` varchar(64) DEFAULT NULL COMMENT '票号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_used` char(1) DEFAULT NULL,
  `used_times` tinyint(2) unsigned DEFAULT NULL,
  `used_time` datetime DEFAULT NULL,
  `used_addr` varchar(100) DEFAULT NULL,
  `check_windown` tinyint(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购票记录';
