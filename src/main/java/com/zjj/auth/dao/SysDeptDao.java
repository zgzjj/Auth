package com.zjj.auth.dao;

import com.zjj.auth.entity.po.SysDeptPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysDeptDao extends JpaRepository<SysDeptPo, String>, JpaSpecificationExecutor<SysDeptPo> {

}
