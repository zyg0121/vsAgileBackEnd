package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.bytewizard.vsagilebackend.service.IAdminInfoService;
import com.bytewizard.vsagilebackend.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author byteWizard
 * @since 2024-09-29
 */
@RestController
@RequestMapping("/adminInfo")
@Tag(name = "AdminInfoController", description = "AdminInfoController")
public class AdminInfoController {

    @Resource
    private IAdminInfoService adminInfoService;

    @Operation(summary = "Get admin info by adminId")
    @Parameter(name = "adminId", description = "adminId", required = true)
    @GetMapping("/{adminId}")
    public JsonData getAdminInfoById(@PathVariable String adminId) {
        List<AdminInfo> adminInfo = adminInfoService.getAdminInfoById(adminId);
        return JsonData.buildSuccess(adminInfo);
    }

}
