package com.bytewizard.vsagilebackend.service.impl;

import com.bytewizard.vsagilebackend.entity.TbProject;
import com.bytewizard.vsagilebackend.mapper.TbProjectMapper;
import com.bytewizard.vsagilebackend.service.ITbProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Service
public class TbProjectServiceImpl extends ServiceImpl<TbProjectMapper, TbProject> implements ITbProjectService {
    TbProjectMapper tbProjectMapper;
    public TbProjectServiceImpl(TbProjectMapper tbProjectMapper) {
        this.tbProjectMapper = tbProjectMapper;
    }
    @Override
    public TbProject getProjectById(Integer projectId) {
        TbProject project = tbProjectMapper.selectById(projectId);
        //check if the project exists
        if (project == null) {
            return null;
        }
        //return project info
        TbProject projectVO = new TbProject();
        projectVO.setProjectId(project.getProjectId());
        projectVO.setProjectName(project.getProjectName());
        projectVO.setProjectPmId(project.getProjectPmId());
        projectVO.setProjectDesc(project.getProjectDesc());
        projectVO.setProjectStartTime(project.getProjectStartTime());
        projectVO.setProjectEndTime(project.getProjectEndTime());
        projectVO.setProjectStatus(project.getProjectStatus());
        return projectVO;
    }
}
