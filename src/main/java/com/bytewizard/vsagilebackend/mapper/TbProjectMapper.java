package com.bytewizard.vsagilebackend.mapper;

import com.bytewizard.vsagilebackend.entity.TbProject;
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
public interface TbProjectMapper extends BaseMapper<TbProject> {
    @Select("select * from `tb_project`")
    Collection<TbProject> selectAllProjects();
}
