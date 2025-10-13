-- 安阳客货邮服务通小程序数据库表结构
-- 数据库: ayhome
-- 专门用于助农产品推广和PC端线上商城引流

-- 用户表 (保持原有结构)
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（加密存储）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像URL',
  `gender` tinyint(1) NULL DEFAULT 0 COMMENT '性别 (0: 未知, 1: 男, 2: 女)',
  `date_of_birth` date NULL DEFAULT NULL COMMENT '出生年月',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- 助农政策分类表
CREATE TABLE `agricultural_policy_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `description` varchar(500) DEFAULT NULL COMMENT '分类描述',
  `sort_order` int DEFAULT '0' COMMENT '排序权重',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='助农政策分类表';

-- 助农政策表
CREATE TABLE `agricultural_policy` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) NOT NULL COMMENT '政策标题',
  `source` varchar(100) NOT NULL COMMENT '发布单位',
  `summary` text COMMENT '政策摘要',
  `content` longtext COMMENT '政策内容（富文本）',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `is_top` tinyint DEFAULT '0' COMMENT '是否置顶 (0: 否, 1: 是)',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `view_count` bigint DEFAULT '0' COMMENT '阅读量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_publish_date` (`publish_date`),
  KEY `idx_is_enabled` (`is_enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='助农政策表';

-- 助农资讯表
CREATE TABLE `agricultural_news` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) NOT NULL COMMENT '资讯标题',
  `thumbnail` varchar(500) DEFAULT NULL COMMENT '缩略图URL',
  `source` varchar(100) NOT NULL COMMENT '资讯来源',
  `content` longtext COMMENT '资讯内容（富文本）',
  `category` varchar(50) DEFAULT NULL COMMENT '资讯分类',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `is_top` tinyint DEFAULT '0' COMMENT '是否置顶 (0: 否, 1: 是)',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `view_count` bigint DEFAULT '0' COMMENT '阅读量',
  `like_count` bigint DEFAULT '0' COMMENT '点赞数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_publish_date` (`publish_date`),
  KEY `idx_is_enabled` (`is_enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='助农资讯表';

-- 助农资讯轮播图表
CREATE TABLE `agricultural_news_banner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `image_url` varchar(500) NOT NULL COMMENT '轮播图URL',
  `target_url` varchar(500) DEFAULT NULL COMMENT '跳转目标URL',
  `title` varchar(200) DEFAULT NULL COMMENT '轮播图标题',
  `sort_order` int DEFAULT '0' COMMENT '排序权重',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='助农资讯轮播图表';

-- 助农产品表
CREATE TABLE `agricultural_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) NOT NULL COMMENT '产品名称',
  `image_url` varchar(500) DEFAULT NULL COMMENT '产品主图URL',
  `images` text COMMENT '产品图集（JSON格式存储多个图片URL）',
  `description` varchar(500) DEFAULT NULL COMMENT '产品简短描述',
  `full_description` longtext COMMENT '产品详细介绍（富文本）',
  `origin` varchar(100) DEFAULT NULL COMMENT '产地信息',
  `nutrition_info` text COMMENT '营养价值信息',
  `planting_process` text COMMENT '种植过程描述',
  `ecommerce_link` varchar(500) DEFAULT 'http://43.123.456.32:8082/' COMMENT 'PC端线上商城链接',
  `sort_order` int DEFAULT '0' COMMENT '排序权重',
  `is_enabled` tinyint DEFAULT '1' COMMENT '是否启用 (0: 禁用, 1: 启用)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_is_enabled` (`is_enabled`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='助农产品表';

-- 插入示例数据

-- 插入助农政策分类数据
INSERT INTO `agricultural_policy_category` (`name`, `description`, `sort_order`, `is_enabled`) VALUES
('农产品扶持', '农产品种植、销售相关扶持政策', 1, 1),
('农业补贴', '农业补贴和资金支持政策', 2, 1),
('农村电商', '农村电商发展扶持政策', 3, 1),
('乡村振兴', '乡村振兴相关政策', 4, 1);

-- 插入示例助农政策数据
INSERT INTO `agricultural_policy` (`title`, `source`, `summary`, `content`, `category_id`, `publish_date`, `is_top`, `is_enabled`, `view_count`) VALUES
('安阳市农产品电商扶持政策实施细则', '安阳市农业农村局', '为促进农产品电商发展，支持农民增收，制定本扶持政策。', '<p>为促进农产品电商发展，支持农民增收，根据国家有关政策，结合我市实际，制定本扶持政策。</p><p>本政策适用于在安阳市行政区域内从事农产品电商的各类主体。</p>', 3, '2024-01-15 10:00:00', 1, 1, 156),
('安阳市农业补贴资金管理办法', '安阳市财政局', '为规范农业补贴资金管理，提高资金使用效益，制定本办法。', '<p>为规范农业补贴资金管理，提高资金使用效益，根据国家有关法律法规，结合我市实际，制定本办法。</p><p>本办法适用于安阳市行政区域内的农业补贴资金管理。</p>', 2, '2024-01-10 14:30:00', 0, 1, 89);

-- 插入示例助农资讯数据
INSERT INTO `agricultural_news` (`title`, `thumbnail`, `source`, `content`, `category`, `publish_date`, `is_top`, `is_enabled`, `view_count`, `like_count`) VALUES
('安阳市成功举办2024年农产品展销会', 'https://example.com/news1.jpg', '安阳日报', '<p>2024年安阳市农产品展销会于近日成功举办，吸引了来自全国各地的采购商前来参观。</p><p>本次展销会以"绿色安阳，优质农产品"为主题，展示了安阳丰富的农产品资源。</p>', '助农要闻', '2024-01-20 09:00:00', 1, 1, 234, 45),
('安阳市新增3个省级农业科技园区', 'https://example.com/news2.jpg', '安阳科技局', '<p>近日，安阳市新增3个省级农业科技园区，为我市农业科技创新发展注入新动力。</p><p>这些园区将重点围绕现代农业、智慧农业、绿色农业等领域开展研究。</p>', '农业科技', '2024-01-18 16:00:00', 0, 1, 167, 32);

-- 插入示例轮播图数据
INSERT INTO `agricultural_news_banner` (`image_url`, `target_url`, `title`, `sort_order`, `is_enabled`) VALUES
('https://example.com/banner1.jpg', '/news/1', '安阳市农产品展销会开幕', 1, 1),
('https://example.com/banner2.jpg', '/news/2', '农业科技创新成果展示', 2, 1),
('https://example.com/banner3.jpg', '/policy/1', '农产品电商扶持政策解读', 3, 1);

-- 插入示例助农产品数据
INSERT INTO `agricultural_product` (`name`, `image_url`, `images`, `description`, `full_description`, `origin`, `nutrition_info`, `planting_process`, `ecommerce_link`, `sort_order`, `is_enabled`) VALUES
('安阳优质苹果', 'https://example.com/product1.jpg', '["https://example.com/product1_1.jpg", "https://example.com/product1_2.jpg"]', '脆甜多汁，营养丰富', '<p>安阳优质苹果是安阳地区的特色农产品，采用有机种植方式，无农药残留。</p><p>果实饱满，口感脆甜，是家庭食用的优质选择。</p>', '安阳市林州市', '富含维生素C、膳食纤维，具有抗氧化、增强免疫力等功效', '采用有机种植方式，全程无农药，自然成熟，人工采摘', 'http://43.123.456.32:8082/', 1, 1),
('安阳绿色蔬菜', 'https://example.com/product2.jpg', '["https://example.com/product2_1.jpg", "https://example.com/product2_2.jpg"]', '新鲜绿色，健康营养', '<p>安阳绿色蔬菜采用无公害种植技术，确保蔬菜的新鲜度和营养价值。</p><p>品种丰富，包括白菜、萝卜、菠菜等多种时令蔬菜。</p>', '安阳市内黄县', '富含维生素A、C、K，叶酸，铁质等营养成分，有助于增强体质', '采用无公害种植技术，定期检测土壤和水质，确保产品安全', 'http://43.123.456.32:8082/', 2, 1);
