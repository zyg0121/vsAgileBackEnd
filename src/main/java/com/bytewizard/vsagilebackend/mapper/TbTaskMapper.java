package com.bytewizard.vsagilebackend.mapper;

import com.bytewizard.vsagilebackend.entity.TbTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Mapper
public interface TbTaskMapper extends BaseMapper<TbTask> {
    @Select("select * from `tb_task` where task_project_id = #{taskProjectId} ")
    Collection<TbTask> selectTaskByProjectId(Integer projectId);
}
