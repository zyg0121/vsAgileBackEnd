package com.bytewizard.vsagilebackend.mapper;

import com.bytewizard.vsagilebackend.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytewizard.vsagilebackend.entity.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

    @Select("select * from `tb_user` where user_name = #{userName} ")
    TbUser selectByName(@Param("userName") String userName);
}
