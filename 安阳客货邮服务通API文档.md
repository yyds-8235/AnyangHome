# 安阳客货邮服务通小程序 API 接口文档

## 基础信息

- **Base URL**: `https://your-api-domain.com/api/v1`
- **认证方式**: 部分接口需要在请求头中携带 `Authorization: <token>`
- **数据格式**: JSON
- **通用响应结构**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

## 1. 个人中心接口 (已完成)

### 1.1 用户认证
- `POST /api/auth/login` - 用户登录
- `POST /api/auth/user/register` - 用户注册
- `POST /api/auth/logout` - 用户登出

### 1.2 用户信息管理
- `GET /api/user/profile` - 获取当前用户信息
- `PUT /api/user/profile` - 更新用户信息
- `PUT /api/user/password` - 修改密码
- `DELETE /api/user/account` - 注销账户

## 2. 助农政策接口

### 2.1 获取助农政策分类列表
- **Endpoint**: `GET /api/agricultural-policies/categories`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "name": "农产品扶持",
      "description": "农产品种植、销售相关扶持政策",
      "sortOrder": 1,
      "isEnabled": 1
    }
  ]
}
```

### 2.2 获取助农政策列表
- **Endpoint**: `GET /api/agricultural-policies`
- **认证**: 否
- **请求参数**:
  - `page`: 页码 (默认: 1)
  - `pageSize`: 每页数量 (默认: 10)
  - `categoryId`: 分类ID (可选)
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": 1,
        "title": "安阳市农产品电商扶持政策实施细则",
        "source": "安阳市农业农村局",
        "summary": "为促进农产品电商发展，支持农民增收，制定本扶持政策。",
        "publishDate": "2024-01-15T10:00:00",
        "viewCount": 156
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1
  }
}
```

### 2.3 获取助农政策详情
- **Endpoint**: `GET /api/agricultural-policies/{id}`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "title": "安阳市农产品电商扶持政策实施细则",
    "source": "安阳市农业农村局",
    "publishDate": "2024-01-15T10:00:00",
    "content": "<p>为促进农产品电商发展，支持农民增收...</p>",
    "viewCount": 157
  }
}
```

## 3. 助农资讯接口

### 3.1 获取助农资讯轮播图
- **Endpoint**: `GET /api/agricultural-news/banners`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "imageUrl": "https://example.com/banner1.jpg",
      "targetUrl": "/news/1",
      "title": "安阳市农产品展销会开幕"
    }
  ]
}
```

### 3.2 获取助农资讯列表
- **Endpoint**: `GET /api/agricultural-news`
- **认证**: 否
- **请求参数**:
  - `page`: 页码 (默认: 1)
  - `pageSize`: 每页数量 (默认: 10)
  - `category`: 资讯分类 (可选)
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": 1,
        "title": "安阳市成功举办2024年农产品展销会",
        "thumbnail": "https://example.com/news1.jpg",
        "source": "安阳日报",
        "publishDate": "2024-01-20T09:00:00",
        "viewCount": 234
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1
  }
}
```

### 3.3 获取助农资讯详情
- **Endpoint**: `GET /api/agricultural-news/{id}`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "title": "安阳市成功举办2024年农产品展销会",
    "source": "安阳日报",
    "publishDate": "2024-01-20T09:00:00",
    "content": "<p>2024年安阳市农产品展销会于近日成功举办...</p>",
    "viewCount": 235,
    "likeCount": 45
  }
}
```

## 4. 助农产品接口

### 4.1 获取助农产品列表
- **Endpoint**: `GET /api/agricultural-products`
- **认证**: 否
- **请求参数**:
  - `page`: 页码 (默认: 1)
  - `pageSize`: 每页数量 (默认: 10)
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": 1,
        "name": "安阳优质苹果",
        "imageUrl": "https://example.com/product1.jpg",
        "description": "脆甜多汁，营养丰富",
        "origin": "安阳市林州市"
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1
  }
}
```

### 4.2 获取助农产品详情
- **Endpoint**: `GET /api/agricultural-products/{id}`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "name": "安阳优质苹果",
    "images": [
      "https://example.com/product1_1.jpg",
      "https://example.com/product1_2.jpg"
    ],
    "fullDescription": "<p>安阳优质苹果是安阳地区的特色农产品...</p>",
    "origin": "安阳市林州市",
    "nutritionInfo": "富含维生素C、膳食纤维，具有抗氧化、增强免疫力等功效",
    "plantingProcess": "采用有机种植方式，全程无农药，自然成熟，人工采摘",
    "ecommerceLink": "http://43.123.456.32:8082/"
  }
}
```

## 错误码说明

- `200`: 操作成功
- `500`: 操作失败
- `404`: 资源不存在
- `401`: 未授权访问

## 注意事项

1. 所有时间格式均为 ISO 8601 格式
2. 图片URL建议使用HTTPS协议
3. 分页查询的页码从1开始
4. 阅读量在获取详情时自动增加
5. 助农产品详情中的电商链接固定为 `http://43.123.456.32:8082/`
6. 所有接口都专注于助农产品推广和PC端线上商城引流
