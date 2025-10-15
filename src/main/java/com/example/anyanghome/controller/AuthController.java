package com.example.anyanghome.controller;

import cn.dev33.satoken.stp.StpUtil;

import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.dto.LoginDTO;
import com.example.anyanghome.pojo.dto.UserRegisterDTO;
import com.example.anyanghome.pojo.entity.User;
import com.example.anyanghome.pojo.vo.LoginSuccessVO;
import com.example.anyanghome.service.UserService;
import com.example.anyanghome.util.PasswordUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "认证接口", description = "用户和商家的登录、注册、注销接口")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "统一登录", description = "根据type字段区分用户或商家登录")
    public Result<LoginSuccessVO> login(@Valid @RequestBody LoginDTO dto) {
        // 用户登录
        User user = userService.getByUsername(dto.getUsername());
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        if (!PasswordUtil.simpleMatches(dto.getPassword(), user.getPassword())) {
            return Result.error("用户名或密码错误");
        }

        StpUtil.login(user.getId());
        StpUtil.getSession().set("userId", user.getId());

        // 返回 LoginSuccessVO<User>，它符合 LoginSuccessVO<?>
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setToken(StpUtil.getTokenInfo().getTokenValue());
        loginSuccessVO.setUserInfo(user);
        return Result.success(loginSuccessVO);

    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<LoginSuccessVO> userRegister(@Valid @RequestBody UserRegisterDTO dto) {
        try {
            // 注册用户
            User user = userService.register(dto);
            
            // 自动登录
            StpUtil.login(user.getId());
            StpUtil.getSession().set("userId", user.getId());
            
            // 返回与登录接口相同的响应
            LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
            loginSuccessVO.setToken(StpUtil.getTokenInfo().getTokenValue());
            loginSuccessVO.setUserInfo(user);
            return Result.success(loginSuccessVO);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "注销登录")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("注销成功");
    }
}

