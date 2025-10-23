# 消息功能 API 文檔

## 概述
消息功能提供用戶消息的創建、查詢、標記已讀等功能，支持微信小程序使用。

## 接口列表

### 1. 創建消息
**接口地址：** `POST /api/message/create`

**請求參數：**
```json
{
    "userId": 1,
    "title": "預約成功通知",
    "content": "您的客車預約已成功，發車時間：2024-01-15 08:00，請提前30分鐘到達車站。",
    "type": "booking"
}
```

**響應示例：**
```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "id": 1,
        "userId": 1,
        "title": "預約成功通知",
        "content": "您的客車預約已成功，發車時間：2024-01-15 08:00，請提前30分鐘到達車站。",
        "type": "booking",
        "isRead": 0,
        "createTime": "2024-01-15T10:30:00",
        "updateTime": "2024-01-15T10:30:00"
    }
}
```

### 2. 分頁查詢消息列表
**接口地址：** `GET /api/message/page`

**請求參數：**
- `userId`: 用戶ID（必填）
- `current`: 當前頁碼（可選，默認1）
- `size`: 每頁大小（可選，默認10）
- `type`: 消息類型（可選，booking/payment/express/system）

**請求示例：**
```
GET /api/message/page?userId=1&current=1&size=10&type=booking
```

**響應示例：**
```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "records": [
            {
                "id": 1,
                "userId": 1,
                "title": "預約成功通知",
                "content": "您的客車預約已成功，發車時間：2024-01-15 08:00，請提前30分鐘到達車站。",
                "type": "booking",
                "isRead": 0,
                "createTime": "2024-01-15T10:30:00",
                "updateTime": "2024-01-15T10:30:00"
            }
        ],
        "total": 1,
        "size": 10,
        "current": 1,
        "pages": 1
    }
}
```

### 3. 獲取未讀消息數量
**接口地址：** `GET /api/message/unread-count`

**請求參數：**
- `userId`: 用戶ID（必填）

**請求示例：**
```
GET /api/message/unread-count?userId=1
```

**響應示例：**
```json
{
    "code": 200,
    "message": "操作成功",
    "data": 2
}
```

### 4. 標記消息為已讀
**接口地址：** `PUT /api/message/mark-read/{messageId}`

**請求參數：**
- `messageId`: 消息ID（路徑參數）

**響應示例：**
```json
{
    "code": 200,
    "message": "操作成功",
    "data": "標記成功"
}
```

### 5. 標記所有消息為已讀
**接口地址：** `PUT /api/message/mark-all-read`

**響應示例：**
```json
{
    "code": 200,
    "message": "操作成功",
    "data": "標記成功"
}
```

## 消息類型說明
- `booking`: 預約相關消息
- `payment`: 支付相關消息
- `express`: 快遞相關消息
- `system`: 系統通知消息

## 使用場景
1. **預約成功通知**：當用戶成功預約客車時，系統自動創建預約成功消息
2. **支付成功通知**：當用戶支付訂單成功時，系統自動創建支付成功消息
3. **快遞寄件通知**：當用戶寄出快遞時，系統自動創建快遞寄件消息
4. **系統通知**：系統維護、活動通知等消息

## 注意事項
1. 所有接口都需要用戶登錄認證（除了創建消息接口）
2. 消息按創建時間倒序排列
3. 未讀消息數量實時更新
4. 標記已讀操作只對當前登錄用戶的消息有效
