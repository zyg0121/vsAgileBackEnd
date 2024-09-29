package com.bytewizard.vsagilebackend.service.impl;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.bytewizard.vsagilebackend.mapper.AdminInfoMapper;
import com.bytewizard.vsagilebackend.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public List<AdminInfo> getAdminInfoById(String adminId) {
        return adminInfoMapper.getAdminInfoById(adminId);
    }

    @Override
    @Transactional
    public boolean insertAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.insert(adminInfo) > 0;
    }

    @Override
    @Transactional
    public boolean updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.updateById(adminInfo) > 0;
    }

    @Override
    @Transactional
    public boolean deleteAdminInfo(String adminId) {
        return adminInfoMapper.deleteById(adminId) > 0;
    }
}
