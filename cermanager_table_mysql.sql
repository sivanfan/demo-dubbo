CREATE TABLE `certificate_info` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `BUSI_NAME` varchar(255) NOT NULL COMMENT '业务单元名',
  `SYS_NAME` varchar(255) NOT NULL COMMENT '模块名',
  `CER_SEND_TYPE` int(10) NOT NULL COMMENT '证书颁发主体 1.邮乐颁发 2.外部颁发',
  `CER_TYPE` int(10) NOT NULL COMMENT '证书类型 1.公钥 2.私钥 3.pfx公私钥',
  `EXPIRATION_DATE` varchar(15) DEFAULT NULL COMMENT '证书过期时间',
  `CHANNEL_CODE` varchar(50) DEFAULT NULL COMMENT '外部颁发证书的通道编码',
  `EMAIL` varchar(50) NOT NULL COMMENT 'email地址',
  `CER_OUT_TYPE` int(10) DEFAULT NULL COMMENT '外部证书类型 1.文件证书 2.秘钥串证书',
  `CER_OUT_FILENAME` varchar(250) DEFAULT NULL COMMENT '外部证书原始文件名',
  `CER_NAME` varchar(50) NOT NULL COMMENT '生成的文件证书名(服务器保存)',
  `DOWN_PASSWORD` varchar(255) DEFAULT NULL COMMENT '访问文件夹服务的密码',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `CER_ENV` int(10) DEFAULT NULL COMMENT '证书使用环境 1.开发 2.测试 3.生产',
  `CER_CONTENT` text COMMENT '证书内容',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `EDIT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证书信息表 ';

CREATE TABLE `system_log` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SYSTEM_CODE` varchar(50) NOT NULL COMMENT '系统标识',
  `REQ_SEQUENCE` varchar(50) NOT NULL COMMENT '请求序列',
  `FUNCTION_ID` varchar(50) NOT NULL COMMENT '请求业务功能码',
  `REQUEST_CONTENT` text COMMENT '请求内容',
  `RESPONSE_CONTENT` text COMMENT '响应内容',
  `USE_TIME` int(11) NOT NULL COMMENT '请求耗时ms',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

CREATE TABLE `task_schedule`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(150) CHARACTER  NULL DEFAULT NULL,
  `job_group` varchar(150) CHARACTER  NULL DEFAULT NULL,
  `cron_expression` varchar(150) CHARACTER  NULL DEFAULT NULL,
  `bean_class` varchar(300) CHARACTER  NULL DEFAULT NULL,
  `spring_id` varchar(150) CHARACTER  NULL DEFAULT NULL,
  `method_name` varchar(150) CHARACTER  NULL DEFAULT NULL,
  `job_status` varchar(6) CHARACTER  NULL DEFAULT NULL COMMENT '0没启动，1启动',
  `is_concurrent` varchar(6) CHARACTER  NULL DEFAULT NULL COMMENT '是否等待上个任务完成，0是等待 1是不等待；',
  `description` varchar(600) CHARACTER  NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '新的定时任务系统表' ;
