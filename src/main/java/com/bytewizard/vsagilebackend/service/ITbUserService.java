package com.bytewizard.vsagilebackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytewizard.vsagilebackend.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytewizard.vsagilebackend.entity.UserDTO;
import com.bytewizard.vsagilebackend.entity.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
public interface ITbUserService extends IService<TbUser> {
    // TODO: Add custom methods here

    // create a new User with the given username, nickname, email, password and role(default 0)
    Integer createUser(UserDTO userDTO);

    // login with the given username and password
    Integer login(String userName, String userPwd);

    // Delete a user by id
    Integer deleteUser(Integer delUserId);

    // Update a user by id
    Integer updateUser(TbUser tbUser);

    // Get a user by id
    UserVO getUserById(Integer userId);

    // Get a user by username
    UserVO getUserByUsername(String username);

    // Get all users
    IPage<TbUser> getAllUsers(Integer pageNum, Integer pageSize);
}
