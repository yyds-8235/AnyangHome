package com.example.anyanghome.service;

import com.example.anyanghome.pojo.dto.PasswordUpdateDTO;
import com.example.anyanghome.pojo.entity.User;
import com.example.anyanghome.pojo.dto.UserRegisterDTO;
import com.example.anyanghome.pojo.dto.UserUpdateDTO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     */
    User register(UserRegisterDTO dto);

    /**
     * 根据用户名查询用户
     */
    User getByUsername(String username);

    /**
     * 根据ID查询用户
     */
    User getById(Long id);

    /**
     * 更新用户信息
     */
    void updateProfile(Long userId, UserUpdateDTO dto);

    /**
     * 修改密码
     */
    void updatePassword(Long userId, PasswordUpdateDTO dto);

    /**
     * 注销账户
     */
    void deleteAccount(Long userId);
}
