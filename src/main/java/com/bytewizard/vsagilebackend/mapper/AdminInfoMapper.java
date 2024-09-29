package com.bytewizard.vsagilebackend.mapper;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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

/*
    @Insert("INSERT INTO admin_info (admin_id, admin_name, admin_email) VALUES (#{adminId}, #{name}, #{email})")
    boolean insertAdminInfo(AdminInfo adminInfo);

    @Update("UPDATE admin_info SET admin_name = #{name}, admin_email = #{email} WHERE admin_id = #{adminId}")
    boolean updateAdminInfo(AdminInfo adminInfo);

    @Delete("DELETE FROM admin_info WHERE admin_id = #{adminId}")
    boolean deleteAdminInfo(String adminId);
*/
}
