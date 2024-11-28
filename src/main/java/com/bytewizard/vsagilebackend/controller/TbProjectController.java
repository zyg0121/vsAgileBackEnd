package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ProjectDTO;
import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.TbProject;
import com.bytewizard.vsagilebackend.entity.UserVO;
import com.bytewizard.vsagilebackend.service.ITbProjectService;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.bytewizard.vsagilebackend.utils.JwtUtil;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@CrossOrigin
@RequestMapping("/project")
public class TbProjectController {
    @Autowired
    public ITbProjectService tbProjectService;
    @Autowired
    private ITbUserService tbUserService;

    public TbProjectController(ITbProjectService tbProjectService) {
        this.tbProjectService = tbProjectService;
    }

    @PostMapping
    public ServerResult createProject(@RequestBody ProjectDTO projectDTO, @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        String username = JwtUtil.extractUsername(token);
        UserVO currentUser = tbUserService.getUserByUsername(username);
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can create project", -1);
        }
        Integer result = tbProjectService.createProject(projectDTO);
        if (result != -1) {
            return new ServerResult(200, "Project created successful", projectDTO);
        }else{
            return new ServerResult(500, "Project created failed", null);
        }
    }

    @GetMapping("/{projectId}")
    public ServerResult getProjects(@PathVariable Integer projectId) {
        TbProject currentProject = tbProjectService.getProjectById(projectId);
        //check if current project exists
        if (currentProject == null) {
            return new ServerResult(401, "Unauthorized", null);
        }
        return new ServerResult(200, "OK", currentProject);
    }

    @GetMapping
    public ServerResult getProjectList(@RequestHeader("Authorization") String token) {
        Collection<TbProject> projectList = tbProjectService.getProjectList();
        token = token.substring(7);
        String username = JwtUtil.extractUsername(token);
        UserVO currentUser = tbUserService.getUserByUsername(username);
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can get project list", -1);
        }
        if(projectList == null|| projectList.isEmpty()){
            return new ServerResult(404, "Get project list failed", null);
        }else{
            return new ServerResult(200, "OK", Map.of("projects", projectList));
        }
    }

    @PutMapping("project/{id}")
    public ServerResult updateProject(@PathVariable Integer id, @RequestBody TbProject tbProject, @RequestHeader("Authorization") String token) {
        tbProject.setProjectId(id);
        token = token.substring(7);
        String username = JwtUtil.extractUsername(token);
        UserVO currentUser = tbUserService.getUserByUsername(username);
        if (currentUser == null || currentUser.getUserRole() != 1) {
            return new ServerResult(403, "Forbidden: Only administrators can update project information", -1);
        }
        if(tbProjectService.updateProject(tbProject)==1){
            return new ServerResult(200, "Update project info", tbProject);
        }else{
            return new ServerResult(500, "Update project failed", -1);
        }
    }

}
