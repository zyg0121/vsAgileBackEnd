package com.bytewizard.vsagilebackend.service.impl;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.bytewizard.vsagilebackend.mapper.AdminInfoMapper;
import com.bytewizard.vsagilebackend.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author byteWizard
 * @since 2024-09-29
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {

    @Override
    public List<AdminInfo> getAdminInfoById(String adminId) {
        return baseMapper.getAdminInfoById(adminId);
    }
}
