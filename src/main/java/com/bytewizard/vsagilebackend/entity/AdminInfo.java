package com.bytewizard.vsagilebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author byteWizard
 * @since 2024-09-29
 */
@Getter
@Setter
@TableName("admin_info")
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    private String adminPwd;

    private String adminName;

    private String adminEmail;

    private String adminTel;

    private String adminRole;

    private String adminStatus;

    private LocalDateTime adminCreateTime;

    private LocalDateTime adminUpdateTime;
}
