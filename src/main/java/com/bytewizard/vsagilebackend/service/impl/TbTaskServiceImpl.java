package com.bytewizard.vsagilebackend.service.impl;

import com.bytewizard.vsagilebackend.entity.TaskDTO;
import com.bytewizard.vsagilebackend.entity.TbTask;
import com.bytewizard.vsagilebackend.mapper.TbTaskMapper;
import com.bytewizard.vsagilebackend.service.ITbTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Service
public class TbTaskServiceImpl extends ServiceImpl<TbTaskMapper, TbTask> implements ITbTaskService {
    @Autowired
    TbTaskMapper tbTaskMapper;


    public TbTaskServiceImpl(TbTaskMapper tbTaskMapper) {
        this.tbTaskMapper = tbTaskMapper;
    }

    @Override
    public Integer CreateTask(TaskDTO taskDTO) {
        // 1. Create a new TbTask object
        TbTask tbTask = new TbTask();
        tbTask.setTaskTitle(taskDTO.getTaskTitle());
        tbTask.setTaskProjectId(taskDTO.getTaskProjectId());
        tbTask.setTaskUserId(taskDTO.getTaskUserId());
        tbTask.setTaskPriority(taskDTO.getTaskPriority());
        tbTask.setTaskStartTime(taskDTO.getTaskStartTime());
        tbTask.setTaskEndTime(taskDTO.getTaskEndTime());
        tbTask.setTaskPreEndTime(taskDTO.getTaskPreEndTime());
        tbTask.setTaskDesc(taskDTO.getTaskDesc());
        tbTask.setTaskStatus(taskDTO.getTaskStatus());

        // 2. Insert the new task into the database
        int result = tbTaskMapper.insert(tbTask);

        // 3. Return the task ID if insertion was successful, otherwise return -1
        return result > 0 ? tbTask.getTaskId() : -1;
    }

    @Override
    public Collection<TbTask> GetTaskList(Integer projectId) {
        return tbTaskMapper.selectTaskByProjectId(projectId);
    }

    @Override
    public TbTask getTaskById(Integer taskId) {
        // 1.判断任务是否存在
        TbTask task = tbTaskMapper.selectById(taskId);
        if (task == null) {
            return null;
        }
        return task;
    }

    @Override
    public Integer updateTask(TbTask tbTask) {
        // 1.判断任务是否存在
        TbTask task = tbTaskMapper.selectById(tbTask.getTaskId());
        if (task == null) {
            return -1;
        }

        tbTaskMapper.updateById(tbTask);

        // 2.更新任务信息
        int result = tbTaskMapper.updateById(tbTask);
        // 3.返回更新结果
        return result == 0 ? -1 : 1;
    }
}
