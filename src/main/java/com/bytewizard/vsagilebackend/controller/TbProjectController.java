package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.ServerResult;
import com.bytewizard.vsagilebackend.entity.TbProject;
import com.bytewizard.vsagilebackend.service.ITbProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

    public ITbProjectService tbProjectService;

    public TbProjectController(ITbProjectService tbProjectService) {
        this.tbProjectService = tbProjectService;
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
}
