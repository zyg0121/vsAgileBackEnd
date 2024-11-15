package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.TbUser;
import com.bytewizard.vsagilebackend.entity.UserVO;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.bytewizard.vsagilebackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class TbUserController {

    @Autowired
    private ITbUserService tbUserService;

    public TbUserController(ITbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @GetMapping("/{userId}")
    public ServerResult getUser(@PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        // 去掉 "Bearer " 前缀，提取实际的 JWT Token
        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        if (currentUser == null) {
            return new ServerResult(401, "Unauthorized", null);
        }

        System.out.println(currentUser.getUserName());

        // 检查用户是否是管理员或请求的 userId 是否与当前用户匹配
        if (currentUser.getUserRole() != 1 && !currentUser.getUserId().equals(userId)) {
            return new ServerResult(403, "Forbidden: You do not have permission to access this resource", null);
        }

        // 获取用户信息
        UserVO user = tbUserService.getUserById(userId);
        if (user == null) {
            return new ServerResult(404, "User Not Found", null);
        }

        return new ServerResult(200, "Get User Info", user);
    }

    @PutMapping
    public ServerResult updateUser(@RequestBody TbUser user, @RequestHeader("Authorization") String token) {
        // 去掉 "Bearer " 前缀，提取实际的 JWT Token
        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        // 检查用户是否是管理员
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can update user information", -1);
        }

        // 更新用户信息
        if (tbUserService.updateUser(user) == 1) {
            return new ServerResult(200, "Update User Info", 1);
        } else {
            return new ServerResult(500, "Update User Info Failed", -1);
        }
    }

    @PostMapping("/getAll")
    public ServerResult getUserList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestHeader("Authorization") String token) {
        // 去掉 "Bearer " 前缀，提取实际的 JWT Token
        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        // 检查用户是否是管理员
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can view the user list", null);
        }

        return new ServerResult(200, "Get User List", tbUserService.getAllUsers(pageNum, pageSize));
    }


}
