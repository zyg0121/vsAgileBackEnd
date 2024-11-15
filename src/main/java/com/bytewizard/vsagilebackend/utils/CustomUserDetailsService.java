package com.bytewizard.vsagilebackend.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bytewizard.vsagilebackend.entity.TbUser; // 请根据实际路径修改
import com.bytewizard.vsagilebackend.mapper.TbUserMapper; // 请根据实际路径修改

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TbUserMapper tbUserMapper;

    // 使用构造函数注入 TbUserMapper
    public CustomUserDetailsService(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查找用户
        TbUser user = tbUserMapper.selectByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到: " + username);
        }

        // 根据 userRole 字段分配角色
        String role = user.getUserRole() == 1 ? "ADMIN" : "USER";

        // 创建并返回 UserDetails 对象
        return User.builder()
                .username(user.getUserName())
                .password(user.getUserPwd()) // 这里的密码应该是加密的
                .roles(role) // 分配角色
                .build();
    }
}

