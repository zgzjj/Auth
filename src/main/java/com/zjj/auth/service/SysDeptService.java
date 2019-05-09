package com.zjj.auth.service;

import com.zjj.auth.common.JsonData;
import com.zjj.auth.dao.SysDeptDao;
import com.zjj.auth.entity.dto.SysDeptDto;
import com.zjj.auth.entity.po.SysDeptPo;
import com.zjj.auth.utils.LevelUtil;
import com.zjj.auth.utils.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    /**
     * 新建部门
     * @param sysDeptDto
     * @return
     */
    public JsonData save(SysDeptDto sysDeptDto){
        SysDeptPo sysDeptPo=new SysDeptPo();
        sysDeptPo = buildSysDeptPo(sysDeptPo,sysDeptDto);
        String parentLevel = sysDeptPo.getParentId()==0 ? "": findById(sysDeptPo.getParentId()).getLevel();
        sysDeptPo.setLevel(LevelUtil.createLevel(parentLevel,sysDeptPo.getParentId()));
        sysDeptPo.setOperatorTime(new Date());
        sysDeptPo.setOperator("");
        if(checkSame(sysDeptPo)){
            return JsonData.fail("同一层级下不能出现同样名称的部门");
        }
        return JsonData.success(sysDeptDao.save(sysDeptPo),"创建部门成功");
    }

    /**
     * 更新部门
     * @param sysDeptDto
     * @return
     */
    public JsonData update(SysDeptDto sysDeptDto){
        SysDeptPo sysDeptPo=findById(sysDeptDto.getId());
        sysDeptPo = buildSysDeptPo(sysDeptPo,sysDeptDto);
        sysDeptPo.setOperatorTime(new Date());
        if(checkSame(sysDeptPo)){
            return JsonData.fail("同一层级下不能出现同样名称的部门");
        }
        return JsonData.success(sysDeptDao.save(sysDeptPo),"更新部门成功");
    }

    /**
     * 校验同一层级下不能出现同样名称的部门
     * @param po
     * @return
     */
    public boolean checkSame(SysDeptPo po){
        Map<String,Object> param =new HashMap<>();
        param.put("EQ_parentId", po.getParentId());
        param.put("EQ_deptName",po.getDeptName());
        List<SysDeptPo> list=findList(param);
        return list.size()>0;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SysDeptPo findById(int id) {
        Optional<SysDeptPo> optional = sysDeptDao.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    /**
     * 按照条件查询列表
     * @param param
     * @return
     */
    public List<SysDeptPo> findList(Map<String, Object> param) {
        return sysDeptDao.findAll(SpecificationUtil.buildSpecification(param, SysDeptPo.class));
    }

    /**
     * 根据条件查询分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public Page<SysDeptPo> findPage(int page, int size, Map<String, Object> param) {
        Sort.Direction direction = Sort.Direction.DESC;
        String sort = "id";
        Pageable pageable = new PageRequest(page-1, size, direction, sort);
        return sysDeptDao.findAll(SpecificationUtil.buildSpecification(param, SysDeptPo.class), pageable);
    }

    public SysDeptPo buildSysDeptPo(SysDeptPo po,SysDeptDto dto){
        po.setDeptName(dto.getDeptName());
        po.setParentId(dto.getParentId());
        po.setSeq(dto.getSeq());
        po.setRemark(dto.getRemark());
        return po;
    }
}
