package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.AutoMaker;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IAutoMakerService extends IService<AutoMaker> {

    Page<AutoMaker> search(int start, int size, AutoMaker autoMaker);
}
