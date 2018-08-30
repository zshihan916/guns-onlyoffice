package com.stylefeng.sso.server.modular.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.sso.plugin.model.LoginUser;
import com.stylefeng.sso.server.modular.entity.SysUser;
import com.stylefeng.sso.server.modular.mapper.SysUserMapper;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-08-29
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    /**
     * 根据用户id获取用户信息
     */
    public LoginUser getUserLoginInfo(Long userId) {

        SysUser sysUser = this.selectById(userId);

        if (sysUser == null) {
            return null;
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setId(sysUser.getUserId());
        loginUser.setAccount(sysUser.getAccount());
        loginUser.setEmail(sysUser.getEmail());
        loginUser.setPhoneNumber(sysUser.getPhone());
        loginUser.setStatus(sysUser.getStatus());
        return loginUser;
    }

}