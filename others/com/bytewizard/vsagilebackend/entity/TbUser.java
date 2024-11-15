package com.bytewizard.vsagilebackend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Getter
@Setter
@TableName("tb_user")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;

    private String userName;

    private String userNickname;

    private String userEmail;

    private String userPwd;

    private Integer userRole;
}
