CREATE TABLE `t_job` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `job_class` varchar(500) DEFAULT NULL,
  `job_param` varchar(255) DEFAULT NULL,
  `running_ip` varchar(16) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enabled_flag` char(1) DEFAULT NULL,
  `grade` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test`.`t_job` (`id`, `name`, `group_name`, `cron_expression`, `job_class`, `job_param`, `running_ip`, `status`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `enabled_flag`, `grade`) 
VALUES ('1', '任务名称-测试', '任务组-分销', '*/5 * * * * ?', 'com.ssm.job.TestJob', 'param1,param2', '', 'STOP', '任务-描述', NULL, '2017-10-19 14:39:30', NULL, '2017-10-31 11:27:43', 'Y', '2');


CREATE TABLE `t_email_config` 
   (`id` VARCHAR(32) NOT NULL, 
	`type_name` VARCHAR(64) NOT NULL, 
	`type_id` VARCHAR(2) NOT NULL, 
	`seller_name` VARCHAR(64) NOT NULL, 
	`seller_id` VARCHAR(10) NOT NULL, 
	`to_email` VARCHAR(2000) NOT NULL, 
	`cc_email` VARCHAR(2000), 
	`flag` CHAR(1) DEFAULT 0, 
	`create_time` datetime DEFAULT NULL, 
	`update_time` datetime DEFAULT NULL, 
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_notification` 
   (	`id` VARCHAR(32) NOT NULL , 
	`title` VARCHAR(200), 
	`type` VARCHAR(50), 
	`to_emails` VARCHAR(2000), 
	`content` VARCHAR(2000), 
	`is_realtime` CHAR(1), 
	`is_sead` CHAR(1), 
	`sead_time` datetime DEFAULT NULL, 
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
