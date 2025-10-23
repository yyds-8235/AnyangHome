-- 约车快递服务数据库表结构
-- 数据库: ayhome

-- 客车路线表
CREATE TABLE `bus_route` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `from_location` varchar(50) NOT NULL COMMENT '出发地',
  `to_location` varchar(50) NOT NULL COMMENT '目的地',
  `departure_time` varchar(10) NOT NULL COMMENT '发车时间',
  `arrival_time` varchar(10) NOT NULL COMMENT '到达时间',
  `price` decimal(10,2) NOT NULL COMMENT '票价',
  `total_seats` int NOT NULL COMMENT '总座位数',
  `available_seats` int NOT NULL COMMENT '可用座位数',
  `bus_type` varchar(50) NOT NULL COMMENT '客车类型',
  `company` varchar(100) NOT NULL COMMENT '客运公司',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_from_to` (`from_location`, `to_location`),
  KEY `idx_departure_time` (`departure_time`),
  KEY `idx_is_enabled` (`is_enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客车路线表';

-- 客车预约表已删除，预约信息存储在统一订单表的details字段中

-- 快递公司表已删除，快递公司信息由前端写死

-- 快递订单表已删除，快递信息存储在统一订单表的details字段中

-- 统一订单表
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `type` varchar(20) NOT NULL COMMENT '订单类型 (booking: 约车, express: 快递)',
  `title` varchar(100) NOT NULL COMMENT '订单标题',
  `description` varchar(200) DEFAULT NULL COMMENT '订单描述',
  `price` decimal(10,2) NOT NULL COMMENT '订单价格',
  `status` varchar(20) DEFAULT 'pending' COMMENT '订单状态 (pending: 待支付, paid: 已支付, completed: 已完成, cancelled: 已取消)',
  `details` text COMMENT '订单详情（JSON格式存储）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_type` (`type`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统一订单表';

-- 插入示例数据

-- 插入客车路线数据
INSERT INTO `bus_route` (`from_location`, `to_location`, `departure_time`, `arrival_time`, `price`, `total_seats`, `available_seats`, `bus_type`, `company`, `is_enabled`) VALUES
('安阳', '郑州', '08:00', '10:30', 45.00, 35, 12, '豪华大巴', '安阳客运公司', 1),
('安阳', '洛阳', '09:30', '12:00', 38.00, 35, 8, '普通客车', '安阳客运公司', 1),
('安阳', '开封', '10:00', '12:30', 35.00, 35, 15, '普通客车', '安阳客运公司', 1),
('安阳', '新乡', '14:00', '16:00', 25.00, 35, 20, '普通客车', '安阳客运公司', 1);

-- 快递公司信息由前端写死，不再需要数据库表

-- 插入示例订单数据
INSERT INTO `order` (`order_no`, `user_id`, `type`, `title`, `description`, `price`, `status`, `details`) VALUES
('ORD001', 1, 'booking', '安阳 → 郑州', '豪华大巴，发车时间：08:00', 45.00, 'paid', '{"from": "安阳", "to": "郑州", "departureTime": "08:00", "arrivalTime": "10:30", "busType": "豪华大巴", "company": "安阳客运公司", "seatNumber": "A05"}'),
('ORD002', 1, 'express', '快递寄件', '顺丰速运，重量：2kg', 24.00, 'paid', '{"senderName": "张三", "senderPhone": "13800138000", "senderAddress": "安阳市文峰区", "receiverName": "李四", "receiverPhone": "13900139000", "receiverAddress": "郑州市金水区", "weight": 2, "content": "文件资料", "company": "顺丰速运"}'),
('ORD003', 1, 'booking', '安阳 → 洛阳', '普通客车，发车时间：09:30', 38.00, 'completed', '{"from": "安阳", "to": "洛阳", "departureTime": "09:30", "arrivalTime": "12:00", "busType": "普通客车", "company": "安阳客运公司", "seatNumber": "B08"}');
