-- ----------------------------
-- Table structure for `wh_key`
-- ----------------------------
DROP TABLE IF EXISTS `wh_key`;
CREATE TABLE `wh_key` (
  `key_id` varchar(255) NOT NULL COMMENT 'keyID',
  `secret_key` varchar(255) NOT NULL COMMENT '秘钥',
  `protocol` varchar(255) DEFAULT NULL COMMENT '使用协议',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IPv4地址',
  `port` int(5) DEFAULT NULL COMMENT '端口',
  `path` varchar(1024) DEFAULT NULL COMMENT '请求路径',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `del` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除；1已删除',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`key_id`),
  UNIQUE KEY `pk_wh_key_id` (`key_id`) USING BTREE,
  KEY `index_wh_key_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for `wh_user`
-- ----------------------------
DROP TABLE IF EXISTS `wh_user`;
CREATE TABLE `wh_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_wh_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1205484131589394435 DEFAULT CHARSET=utf8mb4;
