package com.bytewizard.vsagilebackend.service;

import com.bytewizard.vsagilebackend.entity.TaskDTO;
import com.bytewizard.vsagilebackend.entity.TbTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
public interface ITbTaskService extends IService<TbTask> {
    Integer CreateTask(TaskDTO taskDTO);

    Collection<TbTask> GetTaskList(Integer projectId);

    TbTask getTaskById(Integer taskId);

    Integer updateTask(TbTask tbTask);
}
