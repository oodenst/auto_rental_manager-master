package com.coder.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.AutoMaker;
import com.coder.rental.mapper.AutoMakerMapper;
import com.coder.rental.service.IAutoMakerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {

    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        QueryWrapper<AutoMaker> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_letter")
                .like(StrUtil.isNotEmpty(autoMaker.getName()),"name",autoMaker.getName());
        Page<AutoMaker> page = new Page<>(start, size);
        this.page(page,wrapper);
        return page;
    }
}
