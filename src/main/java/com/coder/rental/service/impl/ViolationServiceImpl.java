package com.coder.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.Violation;
import com.coder.rental.mapper.ViolationMapper;
import com.coder.rental.service.IViolationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class ViolationServiceImpl extends ServiceImpl<ViolationMapper, Violation> implements IViolationService {

    @Override
    public Page<Violation> searchByPage(Page<Violation> page, Violation
            violation) {
        QueryWrapper<Violation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getAutoNum()),
                "auto_num",violation.getAutoNum());
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getReason()),
                "reason",violation.getReason());
        queryWrapper.like(ObjectUtil.isNotEmpty(violation.getLocation()),
                "location",violation.getLocation());
        queryWrapper.le(ObjectUtil.isNotEmpty(violation.getHighVtime()),
                "vtime",violation.getHighVtime());
        queryWrapper.ge(ObjectUtil.isNotEmpty(violation.getLowVtime()),
                "vtime",violation.getLowVtime());
        queryWrapper.le(ObjectUtil.isNotEmpty(violation.getHighFine()),
                "fine",violation.getHighFine());
        queryWrapper.ge(ObjectUtil.isNotEmpty(violation.getLowFine()),
                "fine",violation.getLowFine());
        queryWrapper.eq(ObjectUtil.isNotEmpty(violation.getStatus()),
                "status",violation.getStatus());
        queryWrapper.orderByDesc("vtime");
        return baseMapper.selectPage(page,queryWrapper);
    }
    @Override
    public boolean delete(String ids) {
        List<Integer> list =
                Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        if (!list.isEmpty()) {
            return baseMapper.deleteBatchIds(list) > 0;
        }
        return false;
    }
}
