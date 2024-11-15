package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.UserDTO;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.bytewizard.vsagilebackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ServerResult login(String userName, String userPwd) {
        Integer result = userService.login(userName, userPwd);
        if (result != null && result > 0) {
            // 生成 JWT Token
            String token = JwtUtil.generateToken(userName);
            return new ServerResult(200, "登录成功", token);
        } else {
            return new ServerResult(101, "登录失败", null);
        }
    }
}
