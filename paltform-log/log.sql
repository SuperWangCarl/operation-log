
-- ----------------------------
-- 日志字典表
-- ----------------------------
DROP TABLE IF EXISTS `t_log_meta_dict`;
CREATE TABLE `t_log_meta_dict` (
  `log_dict_id` varchar(36) NOT NULL COMMENT 'ID',
  `request_path` varchar(255) NOT NULL DEFAULT '' COMMENT '请求路径',
  `request_location` varchar(255) NOT NULL DEFAULT '' COMMENT '路径的前台位置 如 xx页面xx按钮',
  `request_zh` varchar(255) NOT NULL DEFAULT '' COMMENT '请求的中文描述',
  `request_package` varchar(255) NOT NULL DEFAULT '' COMMENT '请求的方法名',
  `request_way` varchar(255) NOT NULL DEFAULT '' COMMENT '请求方式 PUT GET POST等',
  `is_record` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否记录 0记录 1不记录',
  `request_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '请求类型 0:用户行为日志 1:数据操作日志 2:异常日志 3:用户登录日志 4:用户访问日志',
  `create_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '创建者的用户ID',
  `modify_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '修改者的用户ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `comment` varchar(255)  NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否可以被删除，0不能被删除，1可被删除，默认为1',
  `use_flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有效数据，0无效（被删除），1有效',
  PRIMARY KEY (`log_dict_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic COMMENT='日志字典表';


-- ----------------------------
-- 日志记录表
-- ----------------------------
DROP TABLE IF EXISTS `t_log_record_data`;
CREATE TABLE `t_log_record_data` (
  `log_data_id` varchar(36) NOT NULL COMMENT 'ID',
  `operation_name` varchar(36) NOT NULL DEFAULT '' COMMENT '操作的用户名',
  `operation_id` varchar(36) NOT NULL DEFAULT '' COMMENT '操作的用户id',
  `ip_address` varchar(36) NOT NULL DEFAULT '' COMMENT '操作的ip地址',
  `request_path` varchar(255) NOT NULL DEFAULT '' COMMENT '请求路径',
  `request_location` varchar(255) NOT NULL DEFAULT '' COMMENT '路径的前台位置 如 xx页面xx按钮',
  `request_zh` varchar(255) NOT NULL DEFAULT '' COMMENT '请求的中文描述',
  `request_package` varchar(1000) NOT NULL DEFAULT '' COMMENT '请求的方法名',
  `request_param_en` varchar(2000) NOT NULL DEFAULT '' COMMENT '操作参数 英文名',
  `request_param_zh` varchar(2000) NOT NULL DEFAULT '' COMMENT '操作参数 中文名',
  `response_param_en` varchar(2000) NOT NULL DEFAULT '' COMMENT '响应参数 英文名',
  `response_param_zh` varchar(2000) NOT NULL DEFAULT '' COMMENT '响应参数 中文名',
  `request_way` varchar(255) NOT NULL DEFAULT '' COMMENT '请求方式 PUT GET POST等',
  `request_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '请求类型 0:用户行为日志 1:数据操作日志 2:异常日志 3:用户登录日志 4:用户访问日志',
  `create_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '创建者的用户ID',
  `modify_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '修改者的用户ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `comment` varchar(255)  NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否可以被删除，0不能被删除，1可被删除，默认为1',
  `use_flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有效数据，0无效（被删除），1有效',
  PRIMARY KEY (`log_data_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic COMMENT='日志记录表';
