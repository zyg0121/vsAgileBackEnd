package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.TaskDTO;
import com.bytewizard.vsagilebackend.entity.TbTask;
import com.bytewizard.vsagilebackend.entity.UserVO;
import com.bytewizard.vsagilebackend.service.ITbTaskService;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.bytewizard.vsagilebackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@RestController
public class TbTaskController {
    @Autowired
    private ITbTaskService tbTaskService;
    @Autowired
    private ITbUserService tbUserService;

    @PostMapping("/task")
    public ServerResult createTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token) {
        // 去掉 "Bearer " 前缀，提取实际的 JWT Token
        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        // 检查用户是否是管理员
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can create task", -1);
        }

        Integer result = tbTaskService.CreateTask(taskDTO);
        if (result != -1) {
            return new ServerResult(200, "创建任务成功", result);
        }else{
            return new ServerResult(500, "创建任务失败", null);
        }
    }

    @GetMapping("/project/{projectId}/task")
    public ServerResult getTaskList(@PathVariable Integer projectId) {
        Collection<TbTask> tbTasks = tbTaskService.GetTaskList(projectId);
        if (tbTasks == null || tbTasks.isEmpty()) {
            return new ServerResult(404, "任务列表获取失败", null);
        }  else {
            return new ServerResult(200, "任务列表获取成功", Map.of("tasks", tbTasks));
        }
    }

    @GetMapping("/task/{id}")
    public ServerResult getTaskById(@PathVariable Integer id) {
        TbTask currentTask = tbTaskService.getTaskById(id);
        if (currentTask == null) {
            return new ServerResult(401, "Unauthorized", null);
        }
        return new ServerResult(200, "OK", currentTask);
    }

    @PutMapping("/task/{id}")
    public ServerResult updateTask(@RequestBody TbTask tbTask, @RequestHeader("Authorization") String token, @PathVariable Integer id) {

        tbTask.setTaskId(id);

        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        // 检查用户是否是管理员
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can update user information", -1);
        }
        // 更新tak信息
        if (tbTaskService.updateTask(tbTask)==1) {
            return new ServerResult(200, "Update Task Info", tbTask);
        } else {
            return new ServerResult(500, "Update Task Info Failed", -1);
        }
    }

    @DeleteMapping("/task/{id}")
    public ServerResult deleteTask(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        token = token.substring(7);

        // 从 Token 中提取用户名
        String username = JwtUtil.extractUsername(token);

        // 使用用户名查询用户信息
        UserVO currentUser = tbUserService.getUserByUsername(username);

        // 检查用户是否存在
        if (currentUser == null) {
            return new ServerResult(403, "Forbidden: Only administrators can delete task", -1);
        }

        if (tbTaskService.deleteTask(id)==1) {
            return new ServerResult(200, "Delete Task", id);
        } else {
            return new ServerResult(500, "Delete Task Failed", -1);
        }
    }
}
