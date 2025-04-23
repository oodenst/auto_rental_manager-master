package com.coder.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoBrand;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface AutoBrandMapper extends BaseMapper<AutoBrand> {
    Page<AutoBrand> searchByPage(@Param("page")Page<AutoBrand> page, @Param("autoBrand")AutoBrand autoBrand);
}
