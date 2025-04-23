package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.Dept;
import com.coder.rental.mapper.DeptMapper;
import com.coder.rental.service.IDeptService;
import com.coder.rental.utils.DeptTreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public List<Dept> select(Dept dept) {
        QueryWrapper <Dept> queryWrapper =new QueryWrapper<>();
        queryWrapper.like(dept.getDeptName()!=null, "dept_name", dept.getDeptName())
                .orderByAsc("order_num");
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        return DeptTreeUtils.buildTree(depts,0);
    }

    @Override
    public List<Dept> selectTree() {
        QueryWrapper <Dept> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        Dept dept = new Dept();
        dept.setDeptName("所有部门").setId(0).setPid(-1);
        depts.add(dept);
        return DeptTreeUtils.buildTree(depts,-1);
    }

    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return baseMapper.selectCount(queryWrapper)>0;
    }
}
