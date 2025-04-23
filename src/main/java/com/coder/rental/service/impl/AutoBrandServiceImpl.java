package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.AutoBrand;
import com.coder.rental.mapper.AutoBrandMapper;
import com.coder.rental.service.IAutoBrandService;
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
public class AutoBrandServiceImpl extends ServiceImpl<AutoBrandMapper, AutoBrand> implements IAutoBrandService {


    @Override
    public Page<AutoBrand> serachByPage(Page<AutoBrand> page, AutoBrand autoBrand) {
        return baseMapper.searchByPage(page,autoBrand);
    }

    @Override
    public List<AutoBrand> selectByMakerId(Integer makerId) {
        QueryWrapper < AutoBrand> query = new QueryWrapper<>();
        query.eq("mid",makerId);
        return baseMapper.selectList(query);
    }
}
