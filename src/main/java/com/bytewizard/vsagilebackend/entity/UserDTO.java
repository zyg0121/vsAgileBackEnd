package com.bytewizard.vsagilebackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 新增用户信息
public class UserDTO {

        private String userName;

        private String userNickname;

        private String userEmail;

        private String userPwd;

}
