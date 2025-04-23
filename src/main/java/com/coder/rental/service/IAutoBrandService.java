package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.AutoBrand;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IAutoBrandService extends IService<AutoBrand> {
    Page<AutoBrand> serachByPage(Page<AutoBrand> page, AutoBrand autoBrand);

    List<AutoBrand> selectByMakerId(Integer makerId);
}
