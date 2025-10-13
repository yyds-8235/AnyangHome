package com.example.anyanghome.pojo.vo;

import com.example.anyanghome.pojo.entity.User;
import lombok.Data;

/**
 * LoginSuccessVO
 */
@Data
public class LoginSuccessVO {
    /**
     * 用户的访问令牌
     */
    private String token;

    /**
     * 存储具体的实体类对象
     */
    private User userInfo;
}
