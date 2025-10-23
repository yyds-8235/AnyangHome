# 约车快递服务 API 接口文档

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

## 1. 约车服务接口

### 1.1 获取可预约的客车路线列表
- **Endpoint**: `GET /api/bus/routes`
- **认证**: 否
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "fromLocation": "安阳",
      "toLocation": "郑州",
      "departureTime": "08:00",
      "arrivalTime": "10:30",
      "price": 45.00,
      "totalSeats": 35,
      "availableSeats": 12,
      "busType": "豪华大巴",
      "company": "安阳客运公司"
    }
  ]
}
```

### 1.2 获取客车路线详情
- **Endpoint**: `GET /api/bus/routes/{id}`
- **认证**: 否
- **路径参数**: `id` - 路线ID
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "fromLocation": "安阳",
    "toLocation": "郑州",
    "departureTime": "08:00",
    "arrivalTime": "10:30",
    "price": 45.00,
    "totalSeats": 35,
    "availableSeats": 12,
    "busType": "豪华大巴",
    "company": "安阳客运公司"
  }
}
```

### 1.3 创建客车预约
- **Endpoint**: `POST /api/bus/create-booking`
- **认证**: 是
- **请求体**:
```json
{
  "routeId": 1,
  "seatNumber": "A05",
  "fromLocation": "安阳",
  "toLocation": "郑州",
  "departureTime": "08:00",
  "arrivalTime": "10:30",
  "busType": "豪华大巴",
  "company": "安阳客运公司",
  "price": 45.00
}
```
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "orderNo": "ORD001",
    "userId": 1,
    "type": "booking",
    "title": "安阳 → 郑州",
    "description": "豪华大巴，发车时间：08:00",
    "price": 45.00,
    "status": "pending"
  }
}
```


## 2. 快递服务接口

### 2.1 创建快递订单
- **Endpoint**: `POST /api/express/create-order`
- **认证**: 是
- **请求体**:
```json
{
  "senderName": "张三",
  "senderPhone": "13800138000",
  "senderAddress": "安阳市文峰区",
  "receiverName": "李四",
  "receiverPhone": "13900139000",
  "receiverAddress": "郑州市金水区",
  "weight": 2,
  "content": "文件资料",
  "companyId": 1,
  "totalPrice": 24
}
```
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "orderNo": "ORD001",
    "userId": 1,
    "type": "express",
    "title": "快递寄件",
    "description": "重量：2kg",
    "price": 24.00,
    "status": "pending"
  }
}
```


## 3. 订单管理接口

### 3.1 获取用户订单列表
- **Endpoint**: `GET /api/orders`
- **认证**: 是
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
        "orderNo": "ORD001",
        "userId": 1,
        "type": "booking",
        "title": "安阳 → 郑州",
        "description": "豪华大巴，发车时间：08:00",
        "price": 45.00,
        "status": "paid",
        "details": "{\"from\": \"安阳\", \"to\": \"郑州\", \"departureTime\": \"08:00\", \"arrivalTime\": \"10:30\", \"busType\": \"豪华大巴\", \"company\": \"安阳客运公司\", \"seatNumber\": \"A05\"}",
        "createTime": "2024-01-14T10:30:00"
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1
  }
}
```

### 3.2 获取订单详情
- **Endpoint**: `GET /api/orders/{id}`
- **认证**: 是
- **路径参数**: `id` - 订单ID
- **响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "orderNo": "ORD001",
    "userId": 1,
    "type": "booking",
    "title": "安阳 → 郑州",
    "description": "豪华大巴，发车时间：08:00",
    "price": 45.00,
    "status": "paid",
    "details": "{\"from\": \"安阳\", \"to\": \"郑州\", \"departureTime\": \"08:00\", \"arrivalTime\": \"10:30\", \"busType\": \"豪华大巴\", \"company\": \"安阳客运公司\", \"seatNumber\": \"A05\"}",
    "createTime": "2024-01-14T10:30:00"
  }
}
```

### 3.3 更新订单状态
- **Endpoint**: `PUT /api/orders/{id}/status`
- **认证**: 是
- **路径参数**: `id` - 订单ID
- **请求体**:
```json
{
  "status": "paid",
  "businessType": "payment",
  "remark": "用户支付完成"
}
```
- **请求体参数说明**:
  - `status` - 订单状态 (pending: 待支付, paid: 已支付, completed: 已完成, cancelled: 已取消)
  - `businessType` - 业务类型 (payment: 支付完成, cancel: 订单取消, complete: 订单完成, other: 其他)
  - `remark` - 备注信息
- **响应示例**:
```json
{
  "code": 200,
  "message": "订单状态更新成功",
  "data": null
}
```

## 4. 支付流程说明

### 4.1 前端支付流程
1. **创建订单**: 调用创建订单接口，订单状态为 `pending`
2. **前端模拟支付**: 前端显示支付界面，模拟支付延时
3. **更新订单状态**: 支付完成后，调用 `PUT /api/orders/{id}/status` 接口，将状态更新为 `paid`
4. **查看订单**: 通过 `GET /api/orders` 接口查看订单状态

### 4.2 支付状态说明
- `pending`: 待支付 - 订单已创建，等待支付
- `paid`: 已支付 - 支付完成
- `completed`: 已完成 - 服务完成
- `cancelled`: 已取消 - 订单取消

## 4. 前端表单数据结构

### 4.1 约车表单数据
```javascript
// 约车表单数据
bookingForm: {
    routeId: 1,
    seatNumber: 'A05',
    from: '安阳',
    to: '郑州',
    departureTime: '08:00',
    arrivalTime: '10:30',
    busType: '豪华大巴',
    company: '安阳客运公司',
    price: 45
}
```

### 4.2 快递表单数据
```javascript
// 快递表单数据
expressForm: {
    senderName: '张三',
    senderPhone: '13800138000',
    senderAddress: '安阳市文峰区',
    receiverName: '李四',
    receiverPhone: '13900139000',
    receiverAddress: '郑州市金水区',
    weight: 2,
    content: '文件资料',
    companyId: 1,
    totalPrice: 24
}
```

## 5. 订单详情数据结构

### 5.1 约车订单详情
```javascript
{
    id: 'ORD001',
    type: 'booking',
    title: '安阳 → 郑州',
    description: '豪华大巴，发车时间：08:00',
    price: 45,
    status: 'paid',
    createTime: '2024-01-14 10:30',
    details: {
        from: '安阳',
        to: '郑州',
        departureTime: '08:00',
        arrivalTime: '10:30',
        busType: '豪华大巴',
        company: '安阳客运公司',
        seatNumber: 'A05'
    }
}
```

### 5.2 快递订单详情
```javascript
{
    id: 'ORD002',
    type: 'express',
    title: '快递寄件',
    description: '顺丰速运，重量：2kg',
    price: 24,
    status: 'paid',
    createTime: '2024-01-14 15:20',
    details: {
        senderName: '张三',
        senderPhone: '13800138000',
        senderAddress: '安阳市文峰区',
        receiverName: '李四',
        receiverPhone: '13900139000',
        receiverAddress: '郑州市金水区',
        weight: 2,
        content: '文件资料',
        company: '顺丰速运'
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
2. 价格使用 decimal(10,2) 类型，精确到分
3. 订单详情使用 JSON 格式存储
4. 约车服务需要检查座位可用性
5. 快递服务需要根据重量计算价格
