package com.zjj.auth.service;

import com.zjj.auth.common.JsonData;
import com.zjj.auth.dao.SysUserDao;
import com.zjj.auth.entity.dto.SysUserDto;
import com.zjj.auth.entity.po.SysUserPo;
import com.zjj.auth.utils.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    public JsonData save(SysUserDto sysUserDto){
        SysUserPo sysUserPo = new SysUserPo();
        sysUserPo = buildSysDeptPo(sysUserPo,sysUserDto);
        if(checkUsernameExist(sysUserPo.getUsername())){
            return JsonData.fail("此用户名已存在");
        }
        if(checkTelephoneExist(sysUserPo.getTelephone())){
            return JsonData.fail("此电话号码已存在");
        }
        if(checkEmailExist(sysUserPo.getEmail())){
            return JsonData.fail("此邮箱已存在");
        }
        String md5Password = DigestUtils.md5DigestAsHex(sysUserPo.getPassword().getBytes());
        sysUserPo.setPassword(md5Password);
        sysUserPo.setOperator("");
        sysUserPo.setOperatorTime(new Date());
        return JsonData.success(sysUserDao.save(sysUserPo),"创建用户成功");
    }

    public JsonData update(SysUserDto sysUserDto){
        SysUserPo sysUserPo = findById(sysUserDto.getId());
        sysUserPo = buildSysDeptPo(sysUserPo,sysUserDto);
        if(checkTelephoneExist(sysUserPo.getTelephone())){
            return JsonData.fail("此电话号码已存在");
        }
        if(checkEmailExist(sysUserPo.getEmail())){
            return JsonData.fail("此邮箱已存在");
        }
        String md5Password = DigestUtils.md5DigestAsHex(sysUserPo.getPassword().getBytes());
        sysUserPo.setPassword(md5Password);
        sysUserPo.setOperator("");
        sysUserPo.setOperatorTime(new Date());
        return JsonData.success(sysUserDao.save(sysUserPo),"更新用户成功");
    }

    /**
     * 校验电话号码是否已存在
     * @param telephone
     * @return
     */
    public boolean checkTelephoneExist(String telephone){
        Map<String,Object> param = new HashMap<>();
        param.put("EQ_telephone", telephone);
        List<SysUserPo> list=findList(param);
        return list.size()>0;
    }

    /**
     * 校验用户名是否已存在
     * @param username
     * @return
     */
    public boolean checkUsernameExist(String username){
        Map<String,Object> param = new HashMap<>();
        param.put("EQ_username", username);
        List<SysUserPo> list=findList(param);
        return list.size()>0;
    }

    /**
     * 校验邮箱是否已存在
     * @param email
     * @return
     */
    public boolean checkEmailExist(String email){
        Map<String,Object> param = new HashMap<>();
        param.put("EQ_email", email);
        List<SysUserPo> list=findList(param);
        return list.size()>0;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SysUserPo findById(int id) {
        Optional<SysUserPo> optional = sysUserDao.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    /**
     * 根据条件查询单个对象
     * @param param
     * @return
     */
    public SysUserPo findUser(Map<String, Object> param) {
        return sysUserDao.findOne(SpecificationUtil.buildSpecification(param, SysUserPo.class)).get();
    }

    /**
     * 按照条件查询列表
     * @param param
     * @return
     */
    public List<SysUserPo> findList(Map<String, Object> param) {
        return sysUserDao.findAll(SpecificationUtil.buildSpecification(param, SysUserPo.class));
    }

    /**
     * 根据条件查询分页
     * @param page
     * @param size
     * @param param
     * @return
     */
    public Page<SysUserPo> findPage(int page, int size, Map<String, Object> param) {
        Sort.Direction direction = Sort.Direction.DESC;
        String sort = "id";
        Pageable pageable = new PageRequest(page-1, size, direction, sort);
        return sysUserDao.findAll(SpecificationUtil.buildSpecification(param, SysUserPo.class), pageable);
    }

    public SysUserPo buildSysDeptPo(SysUserPo po, SysUserDto dto){
        po.setUsername(dto.getUsername());
        po.setPassword(dto.getPassword());
        po.setAvatar(dto.getAvatar());
        po.setTelephone(dto.getTelephone());
        po.setEmail(dto.getEmail());
        po.setDeptId(dto.getDeptId());
        po.setStatus(dto.getStatus());
        po.setRemark(dto.getRemark());
        return po;
    }
}
