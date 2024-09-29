package com.bytewizard.vsagilebackend.controller;

import com.bytewizard.vsagilebackend.entity.AdminInfo;
import com.bytewizard.vsagilebackend.service.IAdminInfoService;
import com.bytewizard.vsagilebackend.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
        return !adminInfo.isEmpty() ? JsonData.buildSuccess(adminInfo) : JsonData.buildError("No data found");
    }

    @Operation(summary = "Get all admin info")
    @GetMapping("/all")
    public JsonData getAllAdminInfo() {
        List<AdminInfo> adminInfo = adminInfoService.list();
        return !adminInfo.isEmpty() ? JsonData.buildSuccess(adminInfo) : JsonData.buildError("No data found");
    }

    @Operation(summary = "Insert admin info")
    @PostMapping("/insert")
    public JsonData insertAdminInfo(@RequestBody AdminInfo adminInfo) {
        boolean result = adminInfoService.insertAdminInfo(adminInfo);
        return result ? JsonData.buildSuccess("Insert Successful") : JsonData.buildError("Insert failed");
    }

    @Operation(summary = "Update admin info")
    @PutMapping("/update")
    public JsonData updateAdminInfo(@RequestBody AdminInfo adminInfo) {
        boolean result = adminInfoService.updateAdminInfo(adminInfo);
        return result ? JsonData.buildSuccess("Update Successful") : JsonData.buildError("Update failed");
    }

    @Operation(summary = "Delete admin info by adminId")
    @DeleteMapping("/delete/{adminId}")
    public JsonData deleteAdminInfo(@PathVariable String adminId) {
        boolean result = adminInfoService.deleteAdminInfo(adminId);
        return result ? JsonData.buildSuccess("Delete Successful") : JsonData.buildError("Delete failed");
    }

}
