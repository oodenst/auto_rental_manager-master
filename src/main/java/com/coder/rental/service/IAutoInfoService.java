package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.AutoInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IAutoInfoService extends IService<AutoInfo> {


    Page<AutoInfo> search(Page<AutoInfo> page, AutoInfo autoInfo);

    boolean delete(String ids);

    AutoInfo selectByAutoNum(String autoNum);

    List<AutoInfo> toBeMaintain();
}
