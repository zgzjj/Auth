package com.zjj.auth.dao;

import com.zjj.auth.entity.po.SysUserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserDao extends JpaRepository<SysUserPo, Integer>, JpaSpecificationExecutor<SysUserPo> {

}
