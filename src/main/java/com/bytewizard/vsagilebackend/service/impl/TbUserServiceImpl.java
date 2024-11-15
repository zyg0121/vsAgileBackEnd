package com.bytewizard.vsagilebackend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bytewizard.vsagilebackend.entity.TbUser;
import com.bytewizard.vsagilebackend.entity.UserDTO;
import com.bytewizard.vsagilebackend.entity.UserVO;
import com.bytewizard.vsagilebackend.mapper.TbUserMapper;
import com.bytewizard.vsagilebackend.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    TbUserMapper tbUserMapper;
    @Autowired
    public TbUserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Integer createUser(UserDTO userDTO) {
        //1.判断用户名是否存在，若存在则不能用
        TbUser tbUser = tbUserMapper.selectByName(userDTO.getUserName());
        try {
            if (tbUser != null) {
                return -1;
            }
            //2.加密密码
            String encodePwd = passwordEncoder.encode(userDTO.getUserPwd());
            //3.创建新用户
            TbUser newUser = new TbUser();
            newUser.setUserName(userDTO.getUserName());
            newUser.setUserNickname(userDTO.getUserNickname());
            newUser.setUserEmail(userDTO.getUserEmail());
            newUser.setUserPwd(encodePwd);
            newUser.setUserRole(0);
            tbUserMapper.insert(newUser);
            // 4. 判断是否插入成功
            if (newUser.getUserId() == null) {
                return -1;
            }
            // 5. 返回新用户的id
            return newUser.getUserId();
        } catch (Exception e) {
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Integer login(String userName, String userPwd) {
        TbUser user = tbUserMapper.selectByName(userName.trim());

        if (user != null) {
            System.out.println("输入的密码: " + userPwd);
            System.out.println("数据库中的加密密码: " + user.getUserPwd());

            if (passwordEncoder.matches(userPwd, user.getUserPwd())) {
                System.out.println("密码匹配成功");
                return 1; // 登录成功
            } else {
                System.out.println("密码匹配失败");
            }
        } else {
            System.out.println("用户未找到");
        }
        return 0; // 登录失败
    }

    @Override
    public Integer deleteUser(Integer delUserId) {
        // 1.判断用户是否存在
        TbUser delUser = tbUserMapper.selectById(delUserId);
        if (delUser == null) {
            return -1;
        }
        // 2.删除用户
        int result = tbUserMapper.deleteById(delUserId);
        // 3.返回删除结果
        return result == 0 ? -1 : 1;
    }

    @Override
    public Integer updateUser(TbUser tbUser) {
        // 1.判断用户是否存在
        TbUser user = tbUserMapper.selectById(tbUser.getUserId());
        if (user == null) {
            return -1;
        }
        TbUser newUser = new TbUser();
        newUser.setUserId(tbUser.getUserId());
        newUser.setUserName(tbUser.getUserName());
        newUser.setUserNickname(tbUser.getUserNickname());
        newUser.setUserEmail(tbUser.getUserEmail());
        newUser.setUserRole(tbUser.getUserRole());
        // 2.更新用户信息
        int result = tbUserMapper.updateById(newUser);
        // 3.返回更新结果
        return result == 0 ? -1 : 1;
    }

    @Override
    public UserVO getUserById(Integer userId) {
        // 1.判断用户是否存在
        TbUser user = tbUserMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        // 2.返回用户信息
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setUserName(user.getUserName());
        userVO.setUserNickname(user.getUserNickname());
        userVO.setUserEmail(user.getUserEmail());
        userVO.setUserRole(user.getUserRole());
        return userVO;
    }

    @Override
    public UserVO getUserByUsername(String username) {
        // 1.判断用户是否存在
        TbUser user = tbUserMapper.selectByName(username);
        if (user == null) {
            return null;
        }
        // 2.返回用户信息
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setUserName(user.getUserName());
        userVO.setUserNickname(user.getUserNickname());
        userVO.setUserEmail(user.getUserEmail());
        userVO.setUserRole(user.getUserRole());
        return userVO;
    }

    @Override
    public IPage<TbUser> getAllUsers(Integer pageNum, Integer pageSize) {
        // 返回所有用户信息
        return tbUserMapper.selectPage(new Page<>(pageNum, pageSize), null);
    }

}
