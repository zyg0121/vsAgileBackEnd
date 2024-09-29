package com.bytewizard.vsagilebackend.mapper;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author byteWizard
 * @since 2024-09-29
 */
@Mapper
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {

    @Select("SELECT * FROM admin_info WHERE admin_id = #{id}")
    List<AdminInfo> getAdminInfoById(String adminId);
}
