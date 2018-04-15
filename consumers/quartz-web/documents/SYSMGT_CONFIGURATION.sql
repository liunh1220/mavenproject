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



INSERT INTO `t_configuration` (`id`, `name`, `key`, `value`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `enabled_flag`) 
VALUES ('1', '单点登陆一级域名', 'sso.domain', '.lnh.com', NULL, NULL, '2013-08-13 00:00:00', NULL, NULL, 'Y');

INSERT INTO `t_configuration` (`id`, `name`, `key`, `value`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `enabled_flag`) 
VALUES ('2', '调度模式', 'sysmgt.job.mode', 'GLOBAL', 'GLOBAL:全局模式,所有服务器都能运行调度;LOCAL:局部模式,只有指定的服务器才能运行调度', NULL, '2013-08-13 00:00:00', NULL, '2013-08-13 00:00:00', 'Y');

INSERT INTO `t_configuration` (`id`, `name`, `key`, `value`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `enabled_flag`) 
VALUES ('3', '调度自动运行服务器', 'sysmgt.job.running.ip', '888.888.888.888', '多个IP以逗号隔开(注：测试环境请不要修改此参数)', NULL, '2012-12-27 00:00:00', NULL, '2012-12-27 00:00:00', 'Y');









