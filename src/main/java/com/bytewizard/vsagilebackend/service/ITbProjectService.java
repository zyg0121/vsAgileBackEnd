package com.bytewizard.vsagilebackend.service;

import com.bytewizard.vsagilebackend.entity.TbProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytewizard.vsagilebackend.entity.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
public interface ITbProjectService extends IService<TbProject> {
    TbProject getProjectById(Integer projectId);
}
