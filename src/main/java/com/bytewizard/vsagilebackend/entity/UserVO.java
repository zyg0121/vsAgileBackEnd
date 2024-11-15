package com.bytewizard.vsagilebackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// 展示给前端的用户信息
public class UserVO {

    private Integer userId;

    private String userName;

    private String userNickname;

    private String userEmail;

    private Integer userRole;
}
