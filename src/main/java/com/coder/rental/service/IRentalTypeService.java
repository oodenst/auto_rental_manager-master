package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.RentalType;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IRentalTypeService extends IService<RentalType> {

    Object searchByPage(Page<RentalType> page, RentalType rentalType);

    boolean delete(String ids);
}
