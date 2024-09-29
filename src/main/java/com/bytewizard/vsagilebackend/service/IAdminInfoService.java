package com.bytewizard.vsagilebackend.service;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author byteWizard
 * @since 2024-09-29
 */
public interface IAdminInfoService extends IService<AdminInfo> {
    public List<AdminInfo> getAdminInfoById(String adminId);

}
