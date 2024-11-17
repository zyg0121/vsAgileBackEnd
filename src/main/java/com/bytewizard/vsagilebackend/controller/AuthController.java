package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.UserDTO;
import com.bytewizard.vsagilebackend.entity.UserVO;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.bytewizard.vsagilebackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final ITbUserService userService;

    public AuthController(ITbUserService userService) {
        this.userService = userService;
    }

    // 用户注册
    @PostMapping("/register")
    public ServerResult register(@RequestBody UserDTO userDTO) {
        Integer result = userService.createUser(userDTO);
        if (result != null && result > 0) {
            return new ServerResult(200, "注册成功", result); // 返回新用户的id
        } else {
            return new ServerResult(500, "注册失败", null);
        }
    }

    // 用户登录
    @PostMapping("/login")
    public ServerResult login(@RequestBody Map<String, String> loginData) {
        // 处理userName和userPwd的特别情况
        String userName = loginData.get("userName");
        String userPwd = loginData.get("userPwd");
        if (userName == null || userName.isEmpty() || userPwd == null || userPwd.isEmpty()) {
            return new ServerResult(401, "用户名或密码不能为空", null);
        }
        Integer result = userService.login(userName, userPwd);
        if (result != null && result > 0) {
            // 生成 JWT Token
            String token = JwtUtil.generateToken(userName);
            // 获取用户的信息
            UserVO user = userService.getUserById(result);
            // 打包返回到接口 包括用户信息和 Token
            return new ServerResult(200, "登录成功", Map.of("token", "Bearer " + token, "user", user));
        } else {
            return new ServerResult(101, "登录失败,请检查用户名或密码", null);
        }
    }
}
