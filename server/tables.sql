CREATE TABLE `wh_key` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'keyID',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '签名秘钥',
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `key_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '钥匙名',
  `status` int NOT NULL DEFAULT '1' COMMENT '是否启用：1启用；0禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_wh_key_user_key` (`username`,`key_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `wh_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_wh_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
