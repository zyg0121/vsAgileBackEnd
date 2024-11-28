package com.bytewizard.vsagilebackend.service;

import com.bytewizard.vsagilebackend.entity.ProjectDTO;
import com.bytewizard.vsagilebackend.entity.TbProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytewizard.vsagilebackend.entity.TbTask;
import com.bytewizard.vsagilebackend.entity.UserVO;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
public interface ITbProjectService extends IService<TbProject> {
    Integer createProject(ProjectDTO projectDTO);

    Collection<TbProject> getProjectList();

    TbProject getProjectById(Integer projectId);

    Integer updateProject(TbProject tbProject);
}
