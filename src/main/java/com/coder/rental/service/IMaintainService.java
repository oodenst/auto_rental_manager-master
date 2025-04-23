package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.Maintain;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IMaintainService extends IService<Maintain> {

    Page<Maintain> searchByPage(Page<Maintain> page, Maintain maintain);

    boolean delete(String ids);
}
