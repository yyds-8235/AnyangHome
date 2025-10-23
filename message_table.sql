-- 创建消息表
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `title` varchar(255) NOT NULL COMMENT '消息标题',
  `content` text NOT NULL COMMENT '消息内容',
  `type` varchar(50) NOT NULL COMMENT '消息类型 (booking: 预约, payment: 支付, express: 快递, system: 系统)',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已读 (0: 未读, 1: 已读)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_type` (`type`),
  KEY `idx_is_read` (`is_read`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- 插入示例数据
INSERT INTO `message` (`user_id`, `title`, `content`, `type`, `is_read`) VALUES
(1, '预约成功通知', '您的客车预约已成功，发车时间：2024-01-15 08:00，请提前30分钟到达车站。', 'booking', 0),
(1, '支付成功通知', '您的订单支付成功，金额：¥45.00，支付方式：微信支付。', 'payment', 0),
(1, '快递寄件通知', '您的快递已成功寄出，运单号：SF1234567890，预计3天内到达。', 'express', 1);
