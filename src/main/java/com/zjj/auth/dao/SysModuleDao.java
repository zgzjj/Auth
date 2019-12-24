package com.zjj.auth.dao;

import com.zjj.auth.entity.po.SysModulePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysModuleDao extends JpaRepository<SysModulePo, String>, JpaSpecificationExecutor<SysModulePo> {

}
