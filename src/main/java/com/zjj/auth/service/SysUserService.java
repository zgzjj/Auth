package com.zjj.auth.service;

import com.zjj.auth.dao.SysUserDao;
import com.zjj.auth.entity.po.SysUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zjj
 * @desc
 * @date 2019/12/24 11:09
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public SysUserPo save(SysUserPo sysUserPo){
        return sysUserDao.save(sysUserPo);
    }
}
